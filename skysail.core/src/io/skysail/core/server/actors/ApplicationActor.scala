package io.skysail.core.server.actors

import akka.actor.{ Actor, ActorLogging, ActorRef, PoisonPill, Props }
import java.util.concurrent.atomic.AtomicInteger

import akka.http.scaladsl.server.RequestContext
import io.skysail.core.akka.ResponseEvent
import io.skysail.core.model.ApplicationModel
import org.osgi.framework.BundleContext
import akka.http.scaladsl.model.Uri
import io.skysail.core.app.SkysailApplication
import io.skysail.core.akka.ControllerActor
import io.skysail.core.akka.Resource
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration.DurationInt

import scala.util.{ Failure, Success }
import akka.event.LoggingReceive

object ApplicationActor {
  case class GetAppModel()
  case class GetApplication()
  case class SkysailContext(ctx: RequestContext, appModel: ApplicationModel, resource: Resource[_], bundleContext: Option[BundleContext], unmatchedPath: Uri.Path) {
    override def toString() = {
      s"SkysailContext(RequestContext(...),ApplicationModel(...),Option[BundleContext],'${unmatchedPath}')"
    }
  }
}

/**
 * An ApplicationActor waits for ... messages
 *
 */
class ApplicationActor(appModel: ApplicationModel, application: SkysailApplication, bundleContext: Option[BundleContext]) extends Actor with ActorLogging {

  implicit val askTimeout: Timeout = 1.seconds

  val cnt = new AtomicInteger(0)

  var nextActor: ActorRef = null
  val originalSender = sender
  var sendBackTo: ActorRef = null

  import context._

  def receive: Receive = LoggingReceive {
    case (ctx: RequestContext, cls: Class[Resource[_]], unmatchedPath: Uri.Path) => {
      sendBackTo = sender
      log debug s"self:   ${self}"
      log debug s"sender: ${sender}"
      log debug ""

      val theClass = cls.newInstance()
      val controllerActor = context.actorOf(Props.apply(classOf[ControllerActor[String]]/*, theClass*/), "controllerActor$" + cnt.getAndIncrement)

      val r = (controllerActor ? ApplicationActor.SkysailContext(ctx, appModel, theClass, bundleContext, unmatchedPath)).mapTo[ResponseEvent[_]]
      r onComplete {
        case Success(value) =>
          log debug s"backto: ${sendBackTo} ! ${value}"; sendBackTo ! value
        case Failure(failure) => log error s"$failure"
      }
    }
    case _: ApplicationActor.GetApplication => sender ! application
    case _: ApplicationActor.GetAppModel =>
      log debug s"self:   ${self}"
      log debug s"sender: ${sender}"
      log debug ""
      sender ! appModel
    case msg: Any => log info s"IN: received unknown message '$msg' in ${this.getClass.getName}"
  }

  override def preRestart(reason: Throwable, message: Option[Any]) {
    log.error(reason, "Restarting due to [{}] when processing [{}]", reason.getMessage, message.getOrElse(""))
  }

}