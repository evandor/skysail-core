package io.skysail.app.demo

import akka.actor.ActorRef
import io.skysail.core.resources.AsyncListResource
import io.skysail.core.resources.AsyncPostResource
import akka.actor.Props
import io.skysail.core.app.SkysailApplication
import io.skysail.core.server.actors.ApplicationActor
import akka.pattern.ask
import io.skysail.core.akka.RequestEvent
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

import scala.util.{Failure, Success}

class ContactsResource extends AsyncListResource[Contact] {
  val appService = new ContactService()

  def get(requestEvent: RequestEvent): Unit = {
    val applicationActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[DemoApplication].getName)
    val r = (applicationActor ? ApplicationActor.GetApplication()).mapTo[DemoApplication]
    import scala.concurrent.ExecutionContext.Implicits.global
    r onComplete {
      case Success(app) => requestEvent.controllerActor ! app.repo.find()
      case Failure(failure) => println(failure)
    }
  }
}

class PostContactResource extends AsyncPostResource[Contact] {

  def get(requestEvent: RequestEvent): Unit = {
    val entityModel = applicationModel.entityModelFor(classOf[Contact])
    if (entityModel.isDefined) {
      println("EM: " + entityModel.get.fields)
      println("EM: " + entityModel.get.description())
      requestEvent.controllerActor ! entityModel.get.description()
    } else {
      requestEvent.controllerActor ! Contact("a@b.com", "Mira", "Gräf") //describe(classOf[Contact])
    }
  }

  def post(requestEvent: RequestEvent): Unit = {

    val applicationActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[DemoApplication].getName)
    val r = (applicationActor ? ApplicationActor.GetApplication()).mapTo[DemoApplication]
    import scala.concurrent.ExecutionContext.Implicits.global

    val user = Contact("vor", "nach", "email")

    r onComplete {
      case Success(app) => app.repo.save(user)
      case Failure(failure) => println(failure)
    }

    //val ua = actorContext.actorOf(Props[UserAggregate])
    //ua ! AddUserCmd(user)
    //applicationModel.ap

    requestEvent.controllerActor ! Contact("a@b.com", "Mira", "Gräf")
  }
}