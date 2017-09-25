package io.skysail.core.app.resources

import io.skysail.core.resources.AsyncEntityResource
import io.skysail.core.model.ApplicationModel
import akka.actor.ActorRef
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.SkysailRootApplication
import io.skysail.core.server.actors.ApplicationActor
import akka.pattern.ask
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

class ModelResource extends AsyncEntityResource[ApplicationModel] {
  
  
  def get(sender: ActorRef, cmd: ProcessCommand): Unit = { println(applicationModel.resourceModels); sender ! applicationModel }

//  //implicit val ec = E
//  def get(sender: ActorRef): Unit = {
//    val appActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[SkysailRootApplication].getName)
//    val m = (appActor ? ApplicationActor.GetAppModel()).mapTo[ApplicationModel]
//    m.onComplete {
//      case Success(model) => sender ! model
//      case Failure(failure) => println(s"Failure: ${failure}")
//    }
//    //sender ! ApplicationModel("name", null, "desc", List())
//  }
}
