package io.skysail.core.server

import akka.actor.{Actor, ActorLogging}
import io.skysail.core.server.BundlesActor.GetResource
import org.osgi.framework.BundleContext

object BundlesActor {
  case class GetResource()
}

class BundlesActor(bundleContext: BundleContext) extends Actor with ActorLogging {

  override def receive: Receive = {
    case caa: GetResource => getResource(caa)
    //case daa: DeleteApplicationActor => deleteApplicationActor(daa)
  }

  def getResource(caa: GetResource): Unit = {
    sender ! bundleContext.getBundle.getResource("application.conf")
  }

}
