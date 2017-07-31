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
import java.util.concurrent.atomic.AtomicInteger
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.SkysailApplication.CreateApplicationActor

class ApplicationActor extends Actor with ActorLogging {
  
  val cnt = new AtomicInteger(0)

  def receive: Actor.Receive = {
    //case rac: InitResourceActorChain => handleInitResourceActorChain(rac)
    //case caa: CreateApplicationActor => createApplicationActor(caa)
    //case s: String =>  HttpResponse(200)
    case msg: Any => log info s"received unknown message '$msg' in ${this.getClass.getName}"; HttpResponse(200)
  }

  
}