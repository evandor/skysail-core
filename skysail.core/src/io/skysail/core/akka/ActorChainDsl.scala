package io.skysail.core.akka

import org.restlet.representation.StringRepresentation
import org.restlet.representation.Representation
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import org.slf4j.LoggerFactory

object ActorChainDsl {

  private val log = LoggerFactory.getLogger(this.getClass())
  
  sealed trait ActorPathElem {
    def cls: Class[_ <: Actor]
  }

  case class Static(cls: Class[_ <: Actor]) extends ActorPathElem

  sealed trait ActorChain[Self] {
    def elems: List[ActorPathElem]
    def build(): ActorRef = {
      implicit val system = ActorSystem()
      
      val (last,rest) = (elems.reverse.head,elems.reverse.tail)
      
      //log info "new " + last.cls.getName + " => null"
      var p:Props = null 
      // = system.actorOf(Props.apply(last.cls,null))
      elems.reverse.foreach(f => {
        //log info "new " + f.cls.getName + " => " + p.getClass.getName
        val q = Props.apply(f.cls,p)
        p = q
      })
      
      system.actorOf(p, "RequestProcessingActor")
    }
  }

  implicit def actorClassToActorChain0(cls: Class[_ <: Actor]) = ActorChain0(Static(cls) :: Nil)

  case class ActorChain0(elems: List[ActorPathElem]) extends ActorChain[ActorChain0] {
    def ==>(s: Class[_ <: Actor]): ActorChain1 = ActorChain1(elems :+ Static(s))
  }

  case class ActorChain1(elems: List[ActorPathElem]) extends ActorChain[ActorChain1] {
    def ==>(s: Class[_ <: Actor]): ActorChain2 = ActorChain2(elems :+ Static(s))
  }

  case class ActorChain2(elems: List[ActorPathElem]) extends ActorChain[ActorChain2] {
    def ==>(s: Class[_ <: Actor]): ActorChain3 = ActorChain3(elems :+ Static(s))
  }

  case class ActorChain3(elems: List[ActorPathElem]) extends ActorChain[ActorChain3] {}

}