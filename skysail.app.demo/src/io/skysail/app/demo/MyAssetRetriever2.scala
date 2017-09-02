package io.skysail.app.demo

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
import io.skysail.core.server.actors.BundleActor

class MyAssetRetriever2(val nextActorsProps: Props, c: Class[_]) extends AbstractRequestHandlerActor {

  override def doResponse(nextActor: ActorRef, res: ResponseEvent[_]) = {
    implicit val askTimeout: Timeout = 1.seconds
    implicit val ec = context.system.dispatcher

    val bundle = res.req.ctx.bundleContext.get.getBundle
    println("BUNDLE: " + bundle)

    val bundleId = bundle.getBundleId
    println("BUNDLE: " + bundleId)

    val file = res.req.ctx.unmatchedPath.toString()
    println("FILE:   " + file)

    val bA = SkysailApplication.getBundleActor(context.system, bundleId)
    val q = (bA ? BundleActor.GetResource(file)).mapTo[URL]

    val ext = getExtension(file)
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
    
    q onFailure {
      case failure => println(failure)
    }
  }

  private def getExtension(fileName: String) = {
    val index = fileName.lastIndexOf('.')
    if (index != 0) {
      fileName.drop(index + 1)
    } else
      ""
  }
}