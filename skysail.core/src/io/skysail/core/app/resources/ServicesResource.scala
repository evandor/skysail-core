package io.skysail.core.app.resources

import io.skysail.core.app.domain.BundleDescriptor
import akka.actor.ActorRef
import akka.pattern.ask
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.domain.Application
import io.skysail.core.security.AuthorizeByRole
import io.skysail.core.server.actors.ApplicationsActor.GetAllApplications

import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.ClassTag
import io.skysail.core.server.actors.BundlesActor
import org.osgi.framework.{Bundle, ServiceReference}
import akka.util.Timeout
import io.skysail.core.akka.RequestEvent

import scala.concurrent.duration.DurationInt
import io.skysail.core.resources.AsyncListResource

import scala.util.Success
import scala.util.Failure
import org.slf4j.LoggerFactory
import io.skysail.core.app.domain.ServiceDescriptor
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

@deprecated // moved to app.osgi
class ServicesResource extends AsyncListResource[ServiceDescriptor] {

  //  @AuthorizeByRole("admin")
  def get(requestEvent: RequestEvent) {
    val bundlesActor = SkysailApplication.getBundlesActor(this.actorContext.system)
    (bundlesActor ? BundlesActor.GetServices())
      .mapTo[List[ServiceReference[_]]]
      .onComplete {
        //  val result = allServiceRefs.map(serviceRef => ServiceDescriptor(serviceRef)).toList
        case Success(s) => requestEvent.controllerActor ! s.map(s => ServiceDescriptor(s)).toList
        case Failure(f) => println(s"failure ${f}")
      }
  }

}