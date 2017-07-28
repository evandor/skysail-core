package io.skysail.core.server

import akka.actor.ActorLogging
import akka.actor.Actor
import io.skysail.core.app.SkysailApplication.InitResourceActorChain
import akka.actor.Props
import akka.http.scaladsl.model.HttpResponse
import akka.pattern.ask
import akka.http.scaladsl.server.Directives._
import akka.util.Timeout
import scala.concurrent.duration.DurationInt

class ApplicationsActor extends Actor with ActorLogging {

  def receive: Actor.Receive = {
    case rac: InitResourceActorChain => handleInitResourceActorChain(rac)
    case msg: Any => log info s"received unknown message '$msg' in ${this.getClass.getName}"
  }

  def handleInitResourceActorChain(rac: InitResourceActorChain) = {
    implicit val askTimeout: Timeout = 3.seconds

    val actor = context.actorOf(Props.apply(rac.cls), "extracted")
    println("PATH: " + actor.path)
    onSuccess((actor ? rac.requestContext).mapTo[HttpResponse]) { result =>
      val r = complete(result)
      //system.stop(actor)
      r
    }
  }
}