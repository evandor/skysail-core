package io.skysail.core.server.actors

import akka.actor.{ Actor, ActorLogging }
import io.skysail.core.server.actors.BundlesActor.GetResource
import org.osgi.framework.BundleContext
import io.skysail.core.server.actors.BundlesActor.GetBundles
import org.osgi.framework.Bundle
import io.skysail.core.server.actors.BundlesActor.CreateBundleActor
import akka.actor.Props
import akka.actor.ActorRef
import akka.event.LoggingReceive
import io.skysail.core.server.actors.BundlesActor.GetCapabilities
import org.osgi.framework.wiring.BundleWiring
import org.osgi.framework.wiring.BundleCapability

object BundlesActor {
  case class GetResource(val path: String)
  case class GetBundles()
  case class CreateBundleActor(b: Bundle)
  case class GetCapabilities()
}

class BundlesActor(bundleContext: BundleContext) extends Actor with ActorLogging {

  val bundleActors = scala.collection.mutable.Map[String, ActorRef]()

  override def receive: Receive = LoggingReceive {
    case gr: GetResource => getResource(gr)
    case gb: GetBundles => getBundles(gb)
    case gc: GetCapabilities => getCapabilities()
    case cb: CreateBundleActor => createBundleActor(cb)
    case msg: Any => log info s"received unknown message '$msg' of type '${msg.getClass().getName}' in ${this.getClass.getName}"
  }

  private def getResource(gr: GetResource): Unit = {
    log info s"getting asset '${gr.path}' from ${bundleContext.getBundle.getSymbolicName}"
    sender ! bundleContext.getBundle.getResource(gr.path)
  }

  private def getBundles(gb: GetBundles) = {
    val bundles = bundleContext.getBundles.toList
    //println("getting bundles: " + bundles)
    sender ! bundles
  }

  private def createBundleActor(cb: CreateBundleActor) = {
    // log debug s"creating BundleActor ${cb.b.getSymbolicName}..."
    val a = context.actorOf(Props.apply(classOf[BundleActor], cb.b), cb.b.getBundleId.toString)
    bundleActors += cb.b.getBundleId.toString -> a
    //log info s"added new ${cb.b.getBundleId.toString} actor to bundlesActor Map, size is now ${bundleActors.size}"
    a
  }

  import scala.collection.JavaConversions._

  private def getCapabilities() {
    val result = bundleContext.getBundles.toList.map(bundle => {
      bundle.getBundleId -> bundle.adapt(classOf[BundleWiring]).getCapabilities(null).toList
    }).toMap
    sender ! result
  }

}
