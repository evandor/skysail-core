package io.skysail.core.server.actors

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorLogging, OneForOneStrategy, Props}
import akka.event.LoggingReceive
import akka.http.scaladsl.model.Uri
import akka.http.scaladsl.server.RequestContext
import akka.pattern.ask
import akka.util.Timeout
import io.skysail.core.akka.{ControllerActor, ResponseEvent}
import io.skysail.core.app.{ApplicationProvider, SkysailApplication}
import io.skysail.core.model.ApplicationModel
import io.skysail.core.resources.Resource
import org.osgi.framework.BundleContext

import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success}

object ApplicationActor {

  case class GetAppModel()

  case class GetApplication()

  case class SkysailContext(cmd: ApplicationActor.ProcessCommand, appModel: ApplicationModel, resource: Resource[_], bundleContext: Option[BundleContext]) {
    override def toString() = {
      s"SkysailContext@${this.hashCode()}(RequestContext([@${cmd.ctx.hashCode()}]),ApplicationModel(...),Option[BundleContext],'${cmd.unmatchedPath}')"
    }
  }

  case class GetMenu()

  case class ProcessCommand(ctx: RequestContext, cls: Class[_ <: Resource[_]], urlParameter: List[String], unmatchedPath: Uri.Path) {
    override def toString() = {
      s"""ProcessCommand(
            [${ctx.hashCode()}]${ctx.toString},
            ${cls.toString},
            '${unmatchedPath}')
       """.stripMargin
    }
  }

  //  case class ProcessPost(ctx: RequestContext, cls: Class[_ <: Resource[_]], unmatchedPath: Uri.Path)
  //  case class ProcessPut(ctx: RequestContext, cls: Class[_ <: Resource[_]], unmatchedPath: Uri.Path)
  //  case class ProcessDelete(ctx: RequestContext, cls: Class[_ <: Resource[_]], unmatchedPath: Uri.Path)
}

/**
  * An ApplicationActor waits for ... messages
  *
  */
class ApplicationActor(appModel: ApplicationModel, application: SkysailApplication, bundleContext: Option[BundleContext]) extends Actor with ActorLogging {

  implicit val askTimeout: Timeout = 1.seconds

  val cnt = new AtomicInteger(0)

  // http://helenaedelson.com/?p=879 !!!
  //var sendBackTo: ActorRef = null

  import context._

  def receive: Receive = LoggingReceive {
    //case ApplicationActor.ProcessCommand(ctx, cls, urlParameter, unmatchedPath) => {
    case cmd:ApplicationActor.ProcessCommand => {
      val sendBackTo = sender()
      log info s"setting sender to ${sendBackTo}"
      val theClass = cmd.cls.newInstance()
      val controllerActor = context.actorOf(Props.apply(classOf[ControllerActor[String]]), "controllerActor$" + cnt.getAndIncrement)

      val r = (controllerActor ? ApplicationActor.SkysailContext(cmd, appModel, theClass, bundleContext)).mapTo[ResponseEvent[_]]
      r onComplete {
        case Success(value) => sendBackTo ! value
        case Failure(failure) => log error s"Failure>>> $failure"
      }
    }
    case _: ApplicationActor.GetApplication => sender ! application
    case _: ApplicationActor.GetAppModel => sender ! appModel
    case _: ApplicationActor.GetMenu => getMenuIfExistent()
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

  /*override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
      case _: ArithmeticException      => log warning "HIER2: RESUMING"; Resume
      case _: NullPointerException     => log warning "HIER2: RESTART"; Restart
      case _: IllegalArgumentException => log warning "HIER2: STOPPIG"; Stop
      case _: Exception                => log warning "HIER2: EACALATE"; Escalate
      case _: scala.NotImplementedError => log warning "HIER2: EACALATE"; Escalate
      case _: Any => log warning "HIER2: XXX"; Resume
    }*/


}