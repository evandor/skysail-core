package io.skysail.core.akka.actors

import java.net.URL

import akka.actor.Props
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity }
import akka.pattern.ask
import akka.util.Timeout
import io.skysail.core.akka.{ AbstractRequestHandlerActor, RequestEvent, Resource, ResponseEvent }
import io.skysail.core.app.domain.Application

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.RequestEntity
import org.json4s.{ DefaultFormats, jackson, native }

import scala.util.Success
import scala.util.Failure
import akka.actor.ActorRef
import java.nio.file.{ Files, Paths }

import akka.http.scaladsl.model._
import io.skysail.core.app.SkysailApplication
import io.skysail.core.server.actors.BundlesActor
import org.osgi.framework.Bundle
import scala.io.Source

class AssetRetriever(val nextActorsProps: Props, c: Class[_]) extends AbstractRequestHandlerActor {

//  override def receive = {
//    // TODO https://wojciechszela.com/blog/2014/10/01/stackable-actor-traits-with-akka-and-scala-explained/
//    case req: RequestEvent => super.receivedRequestEvent(req)
//    case res: ResponseEvent[_] => super.receivedResponseEvent(res)
//    case e: URL => {
//      println("URL" + e)
//      val is = e.openConnection().getInputStream()
//      val ba = Source.fromInputStream(is).map(_.toByte).toArray
//      println(ba)
//    }
//  }

  override def doResponse(nextActor: ActorRef, res: ResponseEvent[_]) = {
    implicit val askTimeout: Timeout = 1.seconds
    implicit val ec = context.system.dispatcher
    val bA = SkysailApplication.getBundlesActor(context.system)
    val q = (bA ? BundlesActor.GetResource("application.conf")).mapTo[URL]

    val ext = getExtension("application.conf")
    val x = MediaTypes.forExtension(ext)

    q onSuccess {
      case value => {
        println("URL: " + value)
        val is = value.openConnection().getInputStream()
        val ba = Source.fromInputStream(is).map(_.toByte).toArray
        //println(ba)
        nextActor ! res.copy(httpResponse = res.httpResponse.copy(entity = HttpEntity(ContentTypes.`text/plain(UTF-8)`, ba)))
      }
    }

//    val fullPath = Paths.get("/Users/carsten/git/tmp/pline/tsconfig.json")
//    println(fullPath)
//    val ext = getExtensions(fullPath.getFileName.toString)
//    val x = MediaTypes.forExtension(ext)
//    val c: ContentType = ContentTypes.`application/json` // ContentType(MediaTypes.forExtension(ext))// .getOrElse(MediaTypes.`text/plain`))
//    val byteArray = Files.readAllBytes(fullPath)
//
//    nextActor ! res.copy(httpResponse = res.httpResponse.copy(entity = HttpEntity(c, byteArray)))
//    //nextActor ! res.copy(httpResponse = res.httpResponse.copy(entity = "hi"))
  }

  private def getExtension(fileName: String) = {
    val index = fileName.lastIndexOf('.')
    if (index != 0) {
      fileName.drop(index + 1)
    } else
      ""
  }
}