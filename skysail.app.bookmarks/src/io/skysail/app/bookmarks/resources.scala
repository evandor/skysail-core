package io.skysail.app.bookmarks

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.unmarshalling.Unmarshaller
import akka.pattern.ask
import akka.stream.ActorMaterializer
import io.skysail.core.akka.{ListResponseEvent, RequestEvent, ResponseEvent}
import io.skysail.core.app.SkysailApplication
import io.skysail.core.resources.{AsyncListResource, AsyncPostResource, AsyncResource}
import io.skysail.core.server.actors.ApplicationActor
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand
import spray.json.{DefaultJsonProtocol, _}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val bookmarkFormat = jsonFormat3(Bookmark)
}

class BookmarksResource extends AsyncListResource[Bookmark] {
  val appService = new ApplicationService()

  def get(requestEvent: RequestEvent): Unit = {
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

  def get(requestEvent: RequestEvent): Unit = {
    val entityModel = applicationModel.entityModelFor(classOf[Bookmark])
    if (entityModel.isDefined) {
      requestEvent.controllerActor ! entityModel.get.description()
    } else {
      requestEvent.controllerActor ! ResponseEvent(requestEvent, Bookmark("", "", "")) //describe(classOf[Contact])
    }
  }

  def post(requestEvent: RequestEvent): Unit = {
    val applicationActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[BookmarksApplication].getName)
    val r = (applicationActor ? ApplicationActor.GetApplication()).mapTo[BookmarksApplication]
    r onComplete {
      case Success(app) => app.repo.save(requestEvent.cmd.entity)
      case Failure(failure) => println(failure)
    }
    requestEvent.controllerActor ! Bookmark(null, "a@b.com", "Mira")
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {

    implicit val materializer = ActorMaterializer()

    val a = Unmarshaller.stringUnmarshaller
      .forContentTypes(ContentTypes.`application/json`)
        .map(_.parseJson.convertTo[Bookmark])

    val entity1 = processCommand.ctx.request.entity
    println ("Entity1" + entity1)
    val b = a.apply(entity1)
    println ("Entity2" + b)

    formFieldMap { map =>
      val entity = Bookmark(null, map.getOrElse("title", "Unknown"), map.getOrElse("url", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }
}

class BookmarkResource extends AsyncResource[Bookmark] {
  override def get(requestEvent: RequestEvent): Unit = {

  }
}