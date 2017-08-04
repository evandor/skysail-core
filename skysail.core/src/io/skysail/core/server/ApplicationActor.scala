package io.skysail.core.server

import akka.actor.{Actor, ActorLogging, ActorRef, PoisonPill, Props}
import io.skysail.core.app.SkysailApplication.InitResourceActorChain
import akka.http.scaladsl.model.HttpResponse
import akka.pattern.ask
import akka.http.scaladsl.server.Directives._
import akka.util.Timeout

import scala.concurrent.duration.DurationInt
import java.util.concurrent.atomic.AtomicInteger

import akka.http.scaladsl.server.RequestContext
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.SkysailApplication.CreateApplicationActor
import io.skysail.core.model.ApplicationModel

object ApplicationActor {
  case class GetAppModel()
}
class ApplicationActor(appModel: ApplicationModel) extends Actor with ActorLogging {
  
  val cnt = new AtomicInteger(0)

  var nextActor: ActorRef = null
  val originalSender = sender
  var sendBackTo: ActorRef = null

  def receive = in

  import context._

  def in: Receive = {
    case (ctx:RequestContext,cls : Class[_])  => {
      log info s"in AppActor... got message ($ctx, $cls)"
      sendBackTo = sender
      nextActor = context.actorOf(Props.apply(cls)) // ResourceActor, e.g. AppsResource
      nextActor ! ctx
      become(out)
    }
    case _: ApplicationActor.GetAppModel => sender ! appModel
    case msg: Any => log info s"received unknown message '$msg' in ${this.getClass.getName}"
  }

  def out: Receive = {
    case _: ApplicationActor.GetAppModel => sender ! appModel
    case e => {
      log info "out AppActor... " + e
      log info "sending to " + sendBackTo
      sendBackTo ! e
      //log info "stopping actor: " + chainRoot
      //context.stop(chainRoot)
      become(in)
      nextActor ! PoisonPill
    }
    //case msg: Any => log info s"received unknown message '$msg' in ${this.getClass.getName}"
  }

  
}