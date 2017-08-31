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
import io.skysail.core.akka.ResourceController

object ApplicationActor {
  case class GetAppModel()
  case class GetApplication()
  case class SkysailContext(ctx: RequestContext, appModel: ApplicationModel, bundleContext: Option[BundleContext], unmatchedPath: Uri.Path)
}
class ApplicationActor(appModel: ApplicationModel, application: SkysailApplication, bundleContext: Option[BundleContext]) extends Actor with ActorLogging {

  val cnt = new AtomicInteger(0)

  var nextActor: ActorRef = null
  val originalSender = sender
  var sendBackTo: ActorRef = null

  def receive = in

  import context._

  def in: Receive = {
    case (ctx: RequestContext, cls: Class[ResourceController[_]], unmatchedPath: Uri.Path) => {
      log debug s"in AppActor... got message ($ctx, $cls)"
      log debug s"in AppActor... unmatched path: (${unmatchedPath})"
      sendBackTo = sender

      val theClass = cls.newInstance()
      println("theClass " + theClass)
      sendBackTo ! "hi"
//      val nA = context.actorOf(Props.apply(classOf[ControllerActor[String]], theClass))
//      println("nA:" + nA)
//      nextActor = context.actorOf(Props.apply(cls)) // ResourceActor, e.g. AppsResource
//      nextActor ! ApplicationActor.SkysailContext(ctx, appModel, bundleContext, unmatchedPath)
      //become(out)
    }
    case _: ApplicationActor.GetApplication => sender ! application
    case _: ApplicationActor.GetAppModel => sender ! appModel
    case msg: Any => log info s"IN: received unknown message '$msg' in ${this.getClass.getName}"
  }

//  def out: Receive = {
//    case _: ApplicationActor.GetAppModel => sender ! appModel
//    case _: ApplicationActor.GetApplication => sender ! application
//    case e: ResponseEvent[_] => {
//      log debug "out AppActor... " + e
//      log debug "sending to " + sendBackTo
//      sendBackTo ! e
//      become(in)
//      nextActor ! PoisonPill
//    }
//    case msg: Any => log info s"OUT: received unknown message '$msg' in ${this.getClass.getName}"
//  }

}