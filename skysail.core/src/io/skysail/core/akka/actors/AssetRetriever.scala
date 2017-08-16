package io.skysail.core.akka.actors

import java.net.URL

import akka.actor.Props
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.pattern.ask
import akka.util.Timeout
import io.skysail.core.akka.{AbstractRequestHandlerActor, RequestEvent, ResourceController, ResponseEvent}
import io.skysail.core.app.domain.Application

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.RequestEntity
import org.json4s.{DefaultFormats, jackson, native}

import scala.util.Success
import scala.util.Failure
import akka.actor.ActorRef
import java.nio.file.{Files, Paths}

import akka.http.scaladsl.model._
import io.skysail.core.app.SkysailApplication
import io.skysail.core.server.BundlesActor
import org.osgi.framework.Bundle

class AssetRetriever(val nextActorsProps: Props) extends AbstractRequestHandlerActor {

  override def receive = {
    case req: RequestEvent => super.receivedRequestEvent(req)
    case res: ResponseEvent[_] => super.receivedResponseEvent(res)
    case e:URL => println("URL" + e)
  }

  override def doResponse(nextActor: ActorRef, res: ResponseEvent[_]) = {
    implicit val askTimeout: Timeout = 1.seconds
    implicit val ec = context.system.dispatcher
    val bA = SkysailApplication.getBundlesActor(context.system)
    bA ! BundlesActor.GetResource()

//    val fullPath = Paths.get("/Users/carsten/git/tmp/pline/tsconfig.json")
//    println(fullPath)
//    val ext = getExtensions(fullPath.getFileName.toString)
//    val x = MediaTypes.forExtension(ext)
//    val c: ContentType = ContentTypes.`application/json` // ContentType(MediaTypes.forExtension(ext))// .getOrElse(MediaTypes.`text/plain`))
//    val byteArray = Files.readAllBytes(fullPath)

    //nextActor ! res.copy(httpResponse = res.httpResponse.copy(entity = HttpEntity(c, byteArray)))
    nextActor ! res.copy(httpResponse = res.httpResponse.copy(entity = "hi"))
  }

  private def getExtensions(fileName: String): String = {

    val index = fileName.lastIndexOf('.')
    if (index != 0) {
      fileName.drop(index + 1)
    } else
      ""
  }
}