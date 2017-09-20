package io.skysail.core.app.resources

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout

import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.domain._
import io.skysail.core.resources._
import io.skysail.core.security.AuthorizeByRole
import io.skysail.core.server.actors.ApplicationsActor.GetAllApplications
import io.skysail.core.server.actors.BundlesActor
import org.osgi.framework.Bundle
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.ClassTag
import scala.concurrent.duration.DurationInt
import scala.util.{ Success, Failure }

class BundlesResource extends AsyncListResource[BundleDescriptor] {

  //  @AuthorizeByRole("admin")
  def get(sender: ActorRef) {
    val bundlesActor = SkysailApplication.getBundlesActor(this.actorContext.system)
    (bundlesActor ? BundlesActor.GetBundles())
      .mapTo[List[Bundle]]
      .onComplete {
        case Success(s) => sender ! s.map(b => BundleDescriptor(b)).toList
        case Failure(f) => println(s"failure ${f}")
      }
  }

}

class BundleResource extends AsyncEntityResource[BundleDetails] {

  //  @AuthorizeByRole("admin")
  def get(sender: ActorRef) {
    val bundlesActor = SkysailApplication.getBundlesActor(this.actorContext.system)
    (bundlesActor ? BundlesActor.GetBundles())
      .mapTo[List[Bundle]]
      .onComplete {
        case Success(s) => sender ! s.map(b => BundleDescriptor(b)).toList
        case Failure(f) => println(s"failure ${f}")
      }
  }

}