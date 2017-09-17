package io.skysail.app.demo

import akka.actor.ActorRef
import io.skysail.core.resources.AsyncListResource
import io.skysail.core.resources.AsyncPostResource
import akka.actor.Props
import io.skysail.core.app.SkysailApplication
import io.skysail.core.server.actors.ApplicationActor
import akka.pattern.ask
import scala.util.{ Success, Failure }

class ContactsResource extends AsyncListResource[Contact] {
  val appService = new ContactService()

  def get(sender: ActorRef): Unit = {
    val applicationActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[DemoApplication].getName)
    val r = (applicationActor ? ApplicationActor.GetApplication()).mapTo[DemoApplication]
    import scala.concurrent.ExecutionContext.Implicits.global
    r onComplete {
      case Success(app) => sender ! app.repo.find()
      case Failure(failure) => println(failure)
    }
  }
}

class PostContactResource extends AsyncPostResource[Contact] {

  def get(sender: ActorRef): Unit = {
    val entityModel = applicationModel.entityModelFor(classOf[Contact])
    if (entityModel.isDefined) {
      println("EM: " + entityModel.get.fields)
      println("EM: " + entityModel.get.description())
      sender ! entityModel.get.description()
    } else {
      sender ! Contact("a@b.com", "Mira", "Gräf") //describe(classOf[Contact])
    }
  }

  def post(sender: ActorRef): Unit = {

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

    sender ! Contact("a@b.com", "Mira", "Gräf")
  }
}