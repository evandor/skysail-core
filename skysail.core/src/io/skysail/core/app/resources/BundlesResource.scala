package io.skysail.core.app.resources

import io.skysail.core.app.domain.BundleDescriptor
import akka.actor.ActorRef
import akka.pattern.ask
import io.skysail.core.akka.actors.ListResourceController
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.domain.Application
import io.skysail.core.security.AuthorizeByRole
import io.skysail.core.server.actors.ApplicationsActor.GetAllApplications

import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.ClassTag
import io.skysail.core.server.actors.BundlesActor
import org.osgi.framework.Bundle
import akka.util.Timeout
import scala.concurrent.duration.DurationInt
import io.skysail.core.akka.actors.AsyncListResource
import scala.util.Success
import scala.util.Failure
import org.slf4j.LoggerFactory

class BundlesResource extends AsyncListResource[BundleDescriptor] {

  private val log = LoggerFactory.getLogger(this.getClass())

  //  @AuthorizeByRole("admin")
  def get(sender: ActorRef): Unit = {
        
    val bundlesActor = SkysailApplication.getBundlesActor(this.actorContext.system)
    
    log debug s"about to send message GetBundles to ${bundlesActor}"
    
    (bundlesActor ? BundlesActor.GetBundles())
      .mapTo[List[Bundle]]
      .onComplete {
        case Success(s) => sender ! s.map(b => BundleDescriptor(b)).toList
        case Failure(f) => log error s"failure ${f}"
      }
  }

}