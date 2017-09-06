package io.skysail.server.auth.none

import domino.DominoActivator
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  //  private class AkkaCapsule(bundleContext: BundleContext) extends ActorSystemActivator with Capsule {
  //
  //    override def start(): Unit = start(bundleContext)
  //    override def stop(): Unit = stop(bundleContext)
  //
  //    def configure(osgiContext: BundleContext, system: ActorSystem): Unit = {
  //      //      log info "Registering Actor System as Service."
  //      //      //registerService(osgiContext, system)
  //      //      log info s"ActorSystem [${system.name}] initialized."
  //      //      actorSystem = system
  //      //      applicationsActor = system.actorOf(Props[ApplicationsActor], Constants.APPLICATIONS_ACTOR_NAME)
  //      //      log info s"created ApplicationsActor with path ${applicationsActor.path}"
  //      //
  //      //      bundlesActor = system.actorOf(Props(new BundlesActor(bundleContext)), Constants.BUNDLES_ACTOR_NAME)
  //      //      log info s"created BundlesActor with path ${bundlesActor.path}"
  //    }
  //
  //    override def getActorSystemName(context: BundleContext): String = "SkysailActorSystem"
  //  }

  whenBundleActive({

    log warn ""
    log warn s"  =================================================="
    log warn s"  |                                                |"
    log warn s"  |  bundle 'SKYSAIL SERVER AUTH NONE' active      |"
    log warn s"  |                                                |"
    log warn s"  | ---------------------------------------------- |"
    log warn s"  |                                                |"
    log warn s"  |  no authentication or authorization available. |"
    log warn s"  |  Every user can do everything.                 |"
    log warn s"  |                                                |"
    log warn s"  =================================================="
    log warn ""

//    val myService = new AuthenticationService()
//    myService.providesService[io.skysail.core.security.AuthenticationService]

    //addCapsule(new AkkaCapsule(bundleContext))

    //    watchServices[ApplicationProvider] {
    //      case AddingService(service, context) => addApplicationProvider(service)
    //      case ModifiedService(service, _) => log info s"Service '$service' modified"
    //      case RemovedService(service, _) => removeApplicationProvider(service)
    //    }
    //
    //    watchBundles {
    //      case AddingBundle(b, context) => bundlesActor ! BundlesActor.CreateBundleActor(b)
    //      case ModifiedBundle(b, _) => log info s"Bundle ${b.getSymbolicName} modified"
    //      case RemovedBundle(b, _) => log info s"Bundle ${b.getSymbolicName} removed"
    //    }
    //
    //    whenConfigurationActive("server") { conf =>
    //      log info s"received configuration for 'server': ${conf}"
    //      val port = Integer.parseInt(conf.getOrElse("port", defaultPort.toString).asInstanceOf[String])
    //      var binding = conf.getOrElse("binding", defaultBinding).asInstanceOf[String]
    //      var authentication = conf.getOrElse("authentication", defaultAuthentication).asInstanceOf[String]
    //      serverConfig = ServerConfig(port, binding, authentication)
    //      routesTracker = new RoutesTracker(actorSystem, serverConfig.authentication)
    //    }

  })

}