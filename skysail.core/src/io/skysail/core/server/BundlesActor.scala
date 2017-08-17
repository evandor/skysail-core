package io.skysail.core.server

import akka.actor.{Actor, ActorLogging}
import io.skysail.core.server.BundlesActor.GetResource
import org.osgi.framework.BundleContext

object BundlesActor {
  case class GetResource(val path: String)
}

class BundlesActor(bundleContext: BundleContext) extends Actor with ActorLogging {

  override def receive: Receive = {
    case gr: GetResource => getResource(gr)
    //case daa: DeleteApplicationActor => deleteApplicationActor(daa)
  }

  def getResource(gr: GetResource): Unit = {
    sender ! bundleContext.getBundle.getResource(gr.path)
  }

}
