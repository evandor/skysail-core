package io.skysail.core.akka

import io.skysail.core.model.LinkRelation
import io.skysail.core.model.ResourceAssociationType
import akka.actor.ActorLogging
import akka.actor.Actor
import akka.actor.Actor._
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import java.util.Date

import akka.actor.ActorDSL
import akka.actor.ActorDSL
import io.skysail.core.akka.dsl.ActorChainDsl
import io.skysail.core.akka.dsl.ActorChainDsl
import io.skysail.core.akka.dsl.ActorChainDsl.ActorChain
import io.skysail.core.model.ApplicationModel
import akka.event.LoggingReceive
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.RequestContext

object ResourceActor {
  case class GetRequest()  
}

abstract class ResourceActor[T] extends Actor with ActorLogging {

  def getLinkRelation() = LinkRelation.CANONICAL
  def linkedResourceClasses(): List[Class[_ <: ResourceActor[_]]] = List()
  val associatedResourceClasses = scala.collection.mutable.ListBuffer[Tuple2[ResourceAssociationType, Class[_ <: ResourceActor[_]]]]()
  val chainRoot: Props

  var chainRootActor: ActorRef = null
  val originalSender = sender
  var sendBackTo: ActorRef = null

  def receive = in

  import context._

  protected def get(): T

  def in: Receive = LoggingReceive {
    case gr: ResourceActor.GetRequest => {
      log info s"got GET Request(1)"
      get()
      //nextActor ! 
    }
    case reqCtx: RequestContext => {
      log debug "in... " + reqCtx
      sendBackTo = sender
      import io.skysail.core.akka.dsl.ActorChainDsl._

      // log info s"MESSAGE: ${chainRoot} ! (${e},${this}"
      chainRootActor = context.actorOf(chainRoot, "RequestProcessingActor")
      chainRootActor ! (reqCtx, this.self)
      become(out)
    }
  }

  def out: Receive = LoggingReceive {
    case gr: ResourceActor.GetRequest => {
      log info s"got GET Request(2)"
      sender ! get()
    }
    case res:ResponseEvent => {
      log debug "out... " + res
      log debug "sending to " + sendBackTo
      sendBackTo ! res
      log debug "stopping actor: " + chainRoot
      context.stop(chainRootActor)
      become(in)
    }
  }

  override def preRestart(reason: Throwable, message: Option[Any]) {
    log.error(reason, "Restarting due to [{}] when processing [{}]", reason.getMessage, message.getOrElse(""))
  }

}
