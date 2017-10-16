package io.skysail.core.app.resources

import akka.pattern.ask
import io.skysail.core.akka.{RequestEvent, ResponseEvent}
import io.skysail.core.app.domain._
import io.skysail.core.app.{SkysailApplication, SkysailRootApplication}
import io.skysail.core.resources._
import io.skysail.core.server.actors.{ApplicationActor, BundlesActor}
import org.osgi.framework.Bundle

class BundlesResource extends AsyncListResource[BundleDescriptor] {

  //  @AuthorizeByRole("admin")
  def get(requestEvent: RequestEvent) {
    val bundlesActor = SkysailApplication.getBundlesActor(this.actorContext.system)
    val bundles = (bundlesActor ? BundlesActor.GetBundles()).mapTo[List[Bundle]]
    reply[Bundle](requestEvent, bundles, s => s.map(b => BundleDescriptor(b)).toList)
  }
}

class BundleResource extends AsyncEntityResource[BundleDetails] {

  //  @AuthorizeByRole("admin")
  def get(requestEvent: RequestEvent) {
    val bundlesActor = SkysailApplication.getBundlesActor(this.actorContext.system)
    val eventualBundle = (bundlesActor ? BundlesActor.GetBundle(requestEvent.cmd.urlParameter.head.toLong)).mapTo[Bundle]
    entityReply[Bundle](requestEvent, eventualBundle, b => BundleDetails(b))
  }

}

class StartBundleResource extends AsyncPostResource[BundleDetails] {
  def get(requestEvent: RequestEvent): Unit = {
    requestEvent.controllerActor ! ResponseEvent(requestEvent, null)
  }

  def post(requestEvent: RequestEvent): Unit = {
    val applicationActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[SkysailRootApplication].getName)
    val r = (applicationActor ? ApplicationActor.GetApplication()).mapTo[SkysailRootApplication]
//    r onComplete {
//      case Success(app) => app.repo.save(requestEvent.cmd.entity)
//      case Failure(failure) => println(failure)
//    }
    requestEvent.controllerActor ! ResponseEvent(requestEvent, null)
  }

//  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
//
//    implicit val materializer = ActorMaterializer()
//
//    val a = Unmarshaller.stringUnmarshaller
//      .forContentTypes(ContentTypes.`application/json`)
//      .map(_.parseJson.convertTo[Bookmark])
//
//    val entity1 = processCommand.ctx.request.entity
//    println("Entity1" + entity1)
//    val b = a.apply(entity1)
//    println("Entity2" + b)
//
//    formFieldMap { map =>
//      val entity = Bookmark(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("url", "Unknown"))
//      super.createRoute(applicationActor, processCommand.copy(entity = entity))
//    }
//  }

}
