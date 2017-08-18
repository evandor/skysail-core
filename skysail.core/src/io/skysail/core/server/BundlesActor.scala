package io.skysail.core.server

import akka.actor.{Actor, ActorLogging}
import io.skysail.core.server.BundlesActor.GetResource
import org.osgi.framework.BundleContext
import io.skysail.core.server.BundlesActor.GetBundles

object BundlesActor {
  case class GetResource(val path: String)
  case class GetBundles()
}

class BundlesActor(bundleContext: BundleContext) extends Actor with ActorLogging {

  override def receive: Receive = {
    case gr: GetResource => getResource(gr)
    case gb: GetBundles => getBundles(gb)
    case msg: Any => log info s"received unknown message '$msg' of type '${msg.getClass().getName}' in ${this.getClass.getName}"
  }

  def getResource(gr: GetResource): Unit = {
    sender ! bundleContext.getBundle.getResource(gr.path)
  }

  def getBundles(gb: GetBundles) = {
    val bundles = bundleContext.getBundles.toList
    println("getting bundles: " + bundles)
    sender ! bundles
  }

}
