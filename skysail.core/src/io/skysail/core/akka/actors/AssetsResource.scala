package io.skysail.core.akka.actors

import io.skysail.core.akka._
import io.skysail.core.dsl.ActorChainDsl._
import io.skysail.core.model.ApplicationModel
import akka.actor.ActorRef
import scala.reflect.ClassTag
import akka.util.Timeout
import scala.concurrent.duration.DurationInt
import akka.http.scaladsl.model.MediaTypes
import io.skysail.core.app.resources.ActorContextAware
import io.skysail.core.app.SkysailApplication
import io.skysail.core.server.actors.BundlesActor
import java.net.URL
import akka.pattern.ask
import scala.io.Source
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.model.HttpEntity

class AssetsResource extends AsyncStaticResource with ActorContextAware {

  override def get(sendBackTo: ActorRef): Unit = {
    implicit val askTimeout: Timeout = 1.seconds
    implicit val ec = actorContext.system.dispatcher
    val bA = SkysailApplication.getBundlesActor(actorContext.system)
    val q = (bA ? BundlesActor.GetResource("application.conf")).mapTo[URL]

    val ext = getExtension("application.conf")
    val x = MediaTypes.forExtension(ext)

    q onSuccess {
      case value => {
        println("URL: " + value)
        val is = value.openConnection().getInputStream()
        val ba = Source.fromInputStream(is).map(_.toByte).toArray
        //println(ba)
        //sendBackTo ! res.copy(httpResponse = res.httpResponse.copy(entity = HttpEntity(ContentTypes.`text/plain(UTF-8)`, ba)))
        sendBackTo ! ControllerActor.MyResponseEntity(HttpEntity(ContentTypes.`text/plain(UTF-8)`, ba))
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