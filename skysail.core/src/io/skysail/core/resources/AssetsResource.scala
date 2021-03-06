package io.skysail.core.resources

import io.skysail.core.akka._
import akka.actor.ActorRef
import akka.util.Timeout

import scala.concurrent.duration.DurationInt
import akka.http.scaladsl.model.MediaTypes
import io.skysail.core.app.SkysailApplication
import io.skysail.core.server.actors.BundlesActor
import java.net.URL

import akka.pattern.ask

import scala.io.Source
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.model.HttpEntity
import akka.actor.actorRef2Scala
import akka.util.Timeout.durationToTimeout
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

class AssetsResource extends AsyncStaticResource with ActorContextAware {

  override def get(requestEvent: RequestEvent): Unit = {
    implicit val askTimeout: Timeout = 1.seconds
    implicit val ec = actorContext.system.dispatcher
    val bA = SkysailApplication.getBundlesActor(actorContext.system)
    val q = (bA ? BundlesActor.GetResource("application.conf")).mapTo[URL]

    val ext = getExtension("application.conf")
    val x = MediaTypes.forExtension(ext)

    q onSuccess {
      case value => {
        val is = value.openConnection().getInputStream()
        val ba = Source.fromInputStream(is).map(_.toByte).toArray
        //println(ba)
        //sendBackTo ! res.copy(httpResponse = res.httpResponse.copy(entity = HttpEntity(ContentTypes.`text/plain(UTF-8)`, ba)))
        requestEvent.controllerActor ! ControllerActor.MyResponseEntity(HttpEntity(ContentTypes.`text/plain(UTF-8)`, ba))
      }
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