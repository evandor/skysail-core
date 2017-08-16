package io.skysail.core.akka.actors

import akka.actor.Props
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity }
import akka.pattern.ask
import akka.util.Timeout
import io.skysail.core.akka.{ AbstractRequestHandlerActor, ResourceController, ResponseEvent }
import io.skysail.core.app.domain.Application

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.RequestEntity
import org.json4s.{ jackson, native, DefaultFormats }
import scala.util.Success
import scala.util.Failure
import akka.actor.ActorRef
import java.nio.file.{ Files, Paths }
import akka.http.scaladsl.model._

class AssetRetriever(val nextActorsProps: Props) extends AbstractRequestHandlerActor {

  override def doResponse(nextActor: ActorRef, res: ResponseEvent[_]) = {
    implicit val askTimeout: Timeout = 1.seconds
    implicit val ec = context.system.dispatcher

    val fullPath = Paths.get("/Users/carsten/git/tmp/pline/tsconfig.json")
    println(fullPath)
    val ext = getExtensions(fullPath.getFileName.toString)
    val x = MediaTypes.forExtension(ext)
    val c: ContentType = ContentTypes.`application/json` // ContentType(MediaTypes.forExtension(ext))// .getOrElse(MediaTypes.`text/plain`))
    val byteArray = Files.readAllBytes(fullPath)

    nextActor ! res.copy(httpResponse = res.httpResponse.copy(entity = HttpEntity(c, byteArray)))
  }

  private def getExtensions(fileName: String): String = {

    val index = fileName.lastIndexOf('.')
    if (index != 0) {
      fileName.drop(index + 1)
    } else
      ""
  }
}