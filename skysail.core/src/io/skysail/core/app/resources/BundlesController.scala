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

class BundlesController extends ListResourceController[BundleDescriptor] with ActorSystemAware {

  implicit val askTimeout: Timeout = 1.seconds

  def get(): List[BundleDescriptor] = {
    val bundlesActor = SkysailApplication.getBundlesActor(this.as)
    //val b = new BundleDescriptor(1,"name", "version", null, null, 32)
    //List[BundleDescriptor](b)
    (bundlesActor ? BundlesActor.GetBundles())
          .mapTo[List[Bundle]]
          .onSuccess { case r => sender ! r.map (b => BundleDescriptor(b)).toList }
    List()
  }

//  private val bundlesActor = SkysailApplication.getBundlesActor(context.system)
//
//  @AuthorizeByRole("admin")
//  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
//    (bundlesActor ? BundlesActor.GetBundles())
//      .mapTo[List[Bundle]]
//      .onSuccess { case r => sender ! r.map (b => BundleDescriptor(b)).toList }
//  }
  override var as = this.as
  override var sender = this.sender
}