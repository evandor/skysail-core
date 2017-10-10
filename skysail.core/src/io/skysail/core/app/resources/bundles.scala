package io.skysail.core.app.resources

import akka.actor.ActorSelection
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, onComplete}
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import io.skysail.core.akka.{RequestEvent, ResponseEventBase}
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.domain._
import io.skysail.core.resources._
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand
import io.skysail.core.server.actors.{ApplicationActor, BundlesActor}
import org.osgi.framework.Bundle

import scala.util.{Failure, Success}

class BundlesResource extends AsyncListResource[BundleDescriptor] {

  //  @AuthorizeByRole("admin")
  def get(requestEvent: RequestEvent) {
    val bundlesActor = SkysailApplication.getBundlesActor(this.actorContext.system)
    val bundles = (bundlesActor ? BundlesActor.GetBundles()).mapTo[List[Bundle]]
    reply[Bundle](requestEvent, bundles, s => s.map(b => BundleDescriptor(b)).toList)
  }
}

class BundleResource extends AsyncEntityResource[BundleDetails] {

  //  @AuthorizeByRole("admin")
  def get(requestEvent: RequestEvent) {
    val bundlesActor = SkysailApplication.getBundlesActor(this.actorContext.system)
    val eventualBundle = (bundlesActor ? BundlesActor.GetBundle(requestEvent.cmd.urlParameter.head.toLong)).mapTo[Bundle]
    reply[Bundle](requestEvent, eventualBundle, b => BundleDetails(b))
  }

}