package io.skysail.core.akka

import akka.actor.{ Props, ActorSystem }
import org.osgi.framework.BundleContext
import akka.osgi.ActorSystemActivator

class Activator extends ActorSystemActivator {

  def configure(context: BundleContext, system: ActorSystem) {
    // optionally register the ActorSystem in the OSGi Service Registry
    registerService(context, system)

    //val someActor = system.actorOf(Props[SomeActor], name = "someName")
    //someActor ! SomeMessage
  }

}