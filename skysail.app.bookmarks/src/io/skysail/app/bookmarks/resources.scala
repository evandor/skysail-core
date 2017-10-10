package io.skysail.app.bookmarks

import akka.http.scaladsl.server.directives.BasicDirectives.extract
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
import akka.util.ByteString
import io.skysail.core.akka.{ListResponseEvent, RequestEvent, ResponseEvent}
import io.skysail.core.app.SkysailApplication
import io.skysail.core.resources.{AsyncListResource, AsyncPostResource}
import io.skysail.core.server.actors.ApplicationActor

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
//    println("E:" + e)

    implicit val system = actorContext.system
    implicit val materializer = ActorMaterializer()
    //val bs: Future[ByteString] = e.toStrict(1 seconds).map { _.data }
    ////val r1 = Await.result(bs, 1 seconds)
    //val s: Future[String] = bs.map(_.utf8String)

    //val res = Await.result(s, 1 seconds)
    //println("Res: " + res)

    val mp = requestEvent.cmd.ctx.request.uri.query().toMap
    //val s = extract(_.requestEvent.cmd.ctx)
    //println ("MP: " + s)
    val user = Bookmark(mp.getOrElse("title", "Unknown"), mp.getOrElse("url", "Unknown"))

   // e.dataBytes.filter(p => p.)
    e.dataBytes.runWith(Sink.fold(ByteString.empty)(_ ++ _)).map(_.utf8String) map { result =>
      println("x: " + result)
      r onComplete {
        case Success(app) => app.repo.save(user)
        case Failure(failure) => println(failure)
      }
    }

    requestEvent.controllerActor ! Bookmark("a@b.com", "Mira")
  }
}