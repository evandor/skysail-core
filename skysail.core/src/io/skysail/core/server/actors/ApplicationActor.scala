package io.skysail.core.server.actors

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{Actor, ActorLogging, Props}
import akka.event.LoggingReceive
import akka.http.scaladsl.model.Uri
import akka.http.scaladsl.server.RequestContext
import akka.pattern.ask
import akka.util.Timeout
import io.skysail.core.akka.{ControllerActor, ResponseEventBase}
import io.skysail.core.app.{ApplicationProvider, SkysailApplication}
import io.skysail.core.model.ApplicationModel
import io.skysail.core.resources.Resource
import io.skysail.core.server.actors.ApplicationActor._
import org.osgi.framework.BundleContext

import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success}

object ApplicationActor {

  case class GetAppModel()

  case class GetApplication()

  case class SkysailContext(cmd: ProcessCommand, appModel: ApplicationModel, resource: Resource[_], bundleContext: Option[BundleContext])

  case class GetMenu()

  case class ProcessCommand(ctx: RequestContext, cls: Class[_ <: Resource[_]], urlParameter: List[String], unmatchedPath: Uri.Path)

}

/**
  * An ApplicationActor handles various messages related to a skysail application.
  *
  * Each (running?) skysail application has an associated ApplicationActor which deals with various messages, the
  * most generic one of which is to handle a ProcessCommand.
  *
  * A ProcessCommand demands a specific resource inside the current application to handle a users HTTP request.
  * This is done using a new ControllerActor, which is sent a SkysailContext message containing a newly created
  * instance of the specific resource mentioned above.
  *
  */
class ApplicationActor(appModel: ApplicationModel, application: SkysailApplication, bundleContext: Option[BundleContext]) extends Actor with ActorLogging {

  implicit val askTimeout: Timeout = 1.seconds

  val cnt = new AtomicInteger(0)

  import context._

  def receive: Receive = LoggingReceive {
    case cmd: ProcessCommand => {
      val routesCreator = sender()
      val resourceInstance = cmd.cls.newInstance()
      val controllerActor = createController

      val skysailContext = SkysailContext(cmd, appModel, resourceInstance, bundleContext)
      val res = (controllerActor ? skysailContext).mapTo[ResponseEventBase]
      res onComplete {
        case Success(responseEvent) => routesCreator ! responseEvent
        case Failure(failure) => log error s"Failure>>> $failure"
      }
    }
    case _: GetApplication => sender ! application
    case _: GetAppModel => sender ! appModel
    case _: GetMenu => getMenuIfExistent()
    case msg: Any => log info s"IN: received unknown message '$msg' in ${this.getClass.getName}"
  }

  override def preRestart(reason: Throwable, message: Option[Any]) {
    log.error(reason, "Restarting due to [{}] when processing [{}]", reason.getMessage, message.getOrElse(""))
  }

  def getMenuIfExistent() = {
    if (application.isInstanceOf[ApplicationProvider]) {
      val appProvider = application.asInstanceOf[ApplicationProvider]
      val optionalMenu = appProvider.menu()
      sender ! optionalMenu
    } else {
      sender ! None
    }
  }

  private def createController = {
    context.actorOf(Props.apply(classOf[ControllerActor[String]]), "controllerActor$" + cnt.getAndIncrement)
  }


}