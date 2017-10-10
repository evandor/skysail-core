package io.skysail.app.bookmarks

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, onComplete, _}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.pattern.ask
import akka.stream.ActorMaterializer
import io.skysail.core.akka.{ListResponseEvent, RequestEvent, ResponseEvent, ResponseEventBase}
import io.skysail.core.app.SkysailApplication
import io.skysail.core.resources.{AsyncListResource, AsyncPostResource}
import io.skysail.core.server.actors.ApplicationActor
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

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


class PostBookmarkResource extends AsyncPostResource[Bookmark] {

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

    //implicit val system = actorContext.system
    //implicit val materializer = ActorMaterializer()

    val mp = requestEvent.cmd.ctx.request.uri.query().toMap
    val user = Bookmark(mp.getOrElse("title", "Unknown"), mp.getOrElse("url", "Unknown"))

    /*e.dataBytes.runWith(Sink.fold(ByteString.empty)(_ ++ _)).map(_.utf8String) map { result =>
      println("x: " + result)
      r onComplete {
        case Success(app) => app.repo.save(user)
        case Failure(failure) => println(failure)
      }
    }*/

    requestEvent.controllerActor ! Bookmark("a@b.com", "Mira")
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    implicit val materializer = ActorMaterializer()

   // val intFuture = Unmarshal("title=a&url=b").to[Bookmark]
   // val int = Await.result(intFuture, 1.second) // don't block in non-test code!


    entity(as[Bookmark]) { bookmark =>
      println("Bookmark: " + bookmark)
      val t = (applicationActor ? processCommand).mapTo[ResponseEventBase]
      onComplete(t) {
        case Success(result) => complete(result.httpResponse)
        case Failure(failure) => /*log error s"Failure>>> ${failure}"; */ complete(StatusCodes.BadRequest, failure)
      }
    }
  }
}