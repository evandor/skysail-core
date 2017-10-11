package io.skysail.app.bookmarks

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, onComplete, _}
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.stream.ActorMaterializer
import io.skysail.core.akka.{ListResponseEvent, RequestEvent, ResponseEvent, ResponseEventBase}
import io.skysail.core.app.SkysailApplication
import io.skysail.core.resources.{AsyncListResource, AsyncPostResource}
import io.skysail.core.server.actors.ApplicationActor
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand
import spray.json.DefaultJsonProtocol

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val bookmarkFormat = jsonFormat2(Bookmark)
}

class BookmarksResource extends AsyncListResource[Bookmark] {
  val appService = new ApplicationService()

  def get(requestEvent: RequestEvent): Unit = {
    //    val r = appService.getApplications().toList
    //    requestEvent.resourceActor ! ResponseEvent(requestEvent, r)
    val applicationActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[BookmarksApplication].getName)
    val r = (applicationActor ? ApplicationActor.GetApplication()).mapTo[BookmarksApplication]
    //reply(requestEvent, app.repo.find())
    r onComplete {
      case Success(app) => requestEvent.controllerActor ! ListResponseEvent(requestEvent, app.repo.find())
      case Failure(failure) => println(failure)
    }

  }
}


class PostBookmarkResource extends AsyncPostResource[Bookmark] with JsonSupport {

  //implicit val executor: ExecutionContext = actorContext.dispatcher

  def get(requestEvent: RequestEvent): Unit = {
    val entityModel = applicationModel.entityModelFor(classOf[Bookmark])
    if (entityModel.isDefined) {
      requestEvent.controllerActor ! entityModel.get.description()
    } else {
      requestEvent.controllerActor ! ResponseEvent(requestEvent, Bookmark("", "")) //describe(classOf[Contact])
    }
  }

  def post(requestEvent: RequestEvent): Unit = {
    val applicationActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[BookmarksApplication].getName)
    val r = (applicationActor ? ApplicationActor.GetApplication()).mapTo[BookmarksApplication]
    val e = requestEvent.cmd.ctx.request.entity

    //val mp = requestEvent.cmd.ctx.request.uri.query().toMap
    //val user = Bookmark(mp.getOrElse("title", "Unknown"), mp.getOrElse("url", "Unknown"))
    r onComplete {
      case Success(app) => app.repo.save(requestEvent.cmd.entity)
      case Failure(failure) => println(failure)
    }

    requestEvent.controllerActor ! Bookmark("a@b.com", "Mira")
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    implicit val materializer = ActorMaterializer()

    formFieldMap { map =>
      //entity(as[Bookmark]) { bookmark =>
      val entity = Bookmark(map.getOrElse("title", "Unknown"), map.getOrElse("url", "Unknown"))
      println("Bookmark: " + entity)
      val t = (applicationActor ? processCommand.copy(entity = entity)).mapTo[ResponseEventBase]
      onComplete(t) {
        case Success(result) => complete(result.httpResponse)
        case Failure(failure) => /*log error s"Failure>>> ${failure}"; */ complete(StatusCodes.BadRequest, failure)
      }
    }
  }
}