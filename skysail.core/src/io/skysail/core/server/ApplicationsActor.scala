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
import io.skysail.core.app.SkysailApplication.{CreateApplicationActor,DeleteApplicationActor}
import akka.actor.ActorRef
import org.slf4j.LoggerFactory

class ApplicationsActor extends Actor with ActorLogging {
  
  val cnt = new AtomicInteger(0)
  
  val appActors = scala.collection.mutable.Map[String,ActorRef]()

  def receive: Actor.Receive = {
    case rac: InitResourceActorChain => handleInitResourceActorChain(rac)
    case caa: CreateApplicationActor => createApplicationActor(caa)
    case daa: DeleteApplicationActor => deleteApplicationActor(daa)
    case msg: Any => log info s"received unknown message '$msg' in ${this.getClass.getName}"
  }

  private def handleInitResourceActorChain(rac: InitResourceActorChain) = {
    implicit val askTimeout: Timeout = 3.seconds

    val actor = context.actorOf(Props.apply(rac.cls), rac.cls.getSimpleName + "-"+cnt.incrementAndGet())
    //println("PATH: " + actor.path)
    onSuccess((actor ? rac.requestContext).mapTo[HttpResponse]) { result =>
      val r = complete(result)
      //system.stop(actor)
      r
    }
  }
  
  private def createApplicationActor(caa: CreateApplicationActor) = {
    log info s"creating ApplicationActor ${caa.cls.getName}..."
    val a = context.actorOf(Props[ApplicationActor], caa.cls.getName)
    appActors += caa.cls.getName -> a
    log info s"added new ${caa.cls.getName} actor to applicationsActors Map, size is now ${appActors.size}"
    a
  }
  
  private def deleteApplicationActor(daa: DeleteApplicationActor) = {
    log info s"deleting ApplicationActor ${daa.cls.getName}..."
    val actor = appActors.remove(daa.cls.getName)
    log info s"deleted ${daa.cls.getName} actor from applicationsActors Map, size is now ${appActors.size}"
    log info s"stopping ${daa.cls.getName} actor"
    actor.map(context.stop(_))
  }
}