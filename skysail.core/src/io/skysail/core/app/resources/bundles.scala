package io.skysail.core.app.resources

import akka.pattern.ask
import io.skysail.core.akka.{RequestEvent, ResponseEvent}
import io.skysail.core.app.domain._
import io.skysail.core.app.{SkysailApplication, SkysailRootApplication}
import io.skysail.core.resources._
import io.skysail.core.server.actors.{ApplicationActor, BundlesActor}
import org.osgi.framework.Bundle

@deprecated
class BundlesResource extends AsyncListResource[BundleDescriptor] {

  //  @AuthorizeByRole("admin")
  def get(requestEvent: RequestEvent) {
    val bundlesActor = SkysailApplication.getBundlesActor(this.actorContext.system)
    val bundles = (bundlesActor ? BundlesActor.GetBundles()).mapTo[List[Bundle]]
    reply[Bundle](requestEvent, bundles, s => s.map(b => BundleDescriptor(b)).toList)
  }
}

@deprecated
class BundleResource extends AsyncEntityResource[BundleDetails] {

  //  @AuthorizeByRole("admin")
  def get(requestEvent: RequestEvent) {
    val bundlesActor = SkysailApplication.getBundlesActor(this.actorContext.system)
    val eventualBundle = (bundlesActor ? BundlesActor.GetBundle(requestEvent.cmd.urlParameter.head.toLong)).mapTo[Bundle]
    entityReply[Bundle](requestEvent, eventualBundle, b => BundleDetails(b))
  }

}

@deprecated
class StartBundleResource extends AsyncPostResource[BundleDetails] {
  def get(requestEvent: RequestEvent): Unit = {
    val bundlesActor = SkysailApplication.getBundlesActor(this.actorContext.system)
    bundlesActor ! BundlesActor.StartBundle(requestEvent.cmd.urlParameter.head.toLong)
    requestEvent.controllerActor ! ResponseEvent(requestEvent, "{}")
  }

  def post(requestEvent: RequestEvent): Unit = {
    val applicationActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[SkysailRootApplication].getName)
    val r = (applicationActor ? ApplicationActor.GetApplication()).mapTo[SkysailRootApplication]
//    r onComplete {
//      case Success(app) => app.repo.save(requestEvent.cmd.entity)
//      case Failure(failure) => println(failure)
//    }
    requestEvent.controllerActor ! ResponseEvent(requestEvent, "{}")
  }


}

@deprecated
class StopBundleResource extends AsyncPostResource[BundleDetails] {
  def get(requestEvent: RequestEvent): Unit = {
    val bundlesActor = SkysailApplication.getBundlesActor(this.actorContext.system)
    bundlesActor ! BundlesActor.StopBundle(requestEvent.cmd.urlParameter.head.toLong)
    requestEvent.controllerActor ! ResponseEvent(requestEvent, "{}")
  }

  def post(requestEvent: RequestEvent): Unit = {
    val applicationActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[SkysailRootApplication].getName)
    val r = (applicationActor ? ApplicationActor.GetApplication()).mapTo[SkysailRootApplication]
    //    r onComplete {
    //      case Success(app) => app.repo.save(requestEvent.cmd.entity)
    //      case Failure(failure) => println(failure)
    //    }
    requestEvent.controllerActor ! ResponseEvent(requestEvent, "{}")
  }


}
