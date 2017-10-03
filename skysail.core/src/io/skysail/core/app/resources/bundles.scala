package io.skysail.core.app.resources

import akka.pattern.ask
import io.skysail.core.akka.RequestEvent
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.domain._
import io.skysail.core.resources._
import io.skysail.core.server.actors.BundlesActor
import org.osgi.framework.Bundle

class BundlesResource extends AsyncListResource[BundleDescriptor] {

  //  @AuthorizeByRole("admin")
  def get(requestEvent: RequestEvent) {
    val bundlesActor = SkysailApplication.getBundlesActor(this.actorContext.system)
    val eventualBundles = (bundlesActor ? BundlesActor.GetBundles()).mapTo[List[Bundle]]
    reply[Bundle](requestEvent, eventualBundles, s => s.map(b => BundleDescriptor(b)).toList)
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