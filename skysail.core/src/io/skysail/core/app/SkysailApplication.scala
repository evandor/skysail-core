package io.skysail.core.app

import java.util.ArrayList
import java.util.Collections
import java.util.ResourceBundle
import java.util.concurrent.atomic.AtomicInteger

import scala.collection.JavaConverters.asScalaBufferConverter
import scala.concurrent.duration.DurationInt
import org.osgi.framework.Bundle
import org.osgi.framework.BundleContext
import org.osgi.service.component.ComponentContext
import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Deactivate
import org.slf4j.LoggerFactory
import akka.actor.ActorSystem
import akka.actor.Props
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.Directives.get
import akka.http.scaladsl.server.Directives.path
import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import io.skysail.core.restlet.services.ResourceBundleProvider
import io.skysail.core.restlet.utils.ScalaReflectionUtils
import io.skysail.core.akka.ResourceActor
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import io.skysail.core.model.ApplicationModel
import akka.actor.ActorRef
import akka.actor.Status.{Failure, Success}
import io.skysail.core.akka.actors.CounterActor
import io.skysail.core.akka.PrivateMethodExposer
import io.skysail.core.server.ApplicationsActor
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.util.Random
import scala.concurrent.Future
import scala.concurrent.Await
import io.skysail.core.app.SkysailApplication._

object SkysailApplication {
  val log = LoggerFactory.getLogger(classOf[SkysailApplication])

  case class InitResourceActorChain(val requestContext: RequestContext, val cls: Class[_ <: ResourceActor[_]])
  case class CreateApplicationActor(val cls: Class[_ <: SkysailApplication])
  case class DeleteApplicationActor(val cls: Class[_ <: SkysailApplication])

  def getApplicationsActor(system: ActorSystem) = {
    val applicationsActorPath = "/user/" + classOf[ApplicationsActor].getSimpleName
    log info s"searching applicationsActor @ path '${applicationsActorPath}' in system ${system}"
    val applicationsActorSelection = system.actorSelection(applicationsActorPath)
    val r = applicationsActorSelection.resolveOne(2.seconds)
    Await.result(r, 1.seconds)
  }
  
  def getApplicationActorSelection(system: ActorSystem, name: String) = {
    val applicationActorPath = "/user/" + classOf[ApplicationsActor].getSimpleName + "/"+name
    system.actorSelection(applicationActorPath)
  }
}

abstract class SkysailApplication(name: String, val apiVersion: ApiVersion) extends ApplicationRoutesProvider
    with ResourceBundleProvider {

  val log = LoggerFactory.getLogger(classOf[SkysailApplication])

  def this(name: String) = this(name, new ApiVersion(1))

  val appModel = ApplicationModel(name, apiVersion)

  def routesMappings: List[(String, Class[_ <: ResourceActor[_]])]

  var actorRefsMap = Map.empty[String, ActorRef]

  //  lazy val applicationActor = {
  //    val applicationsActorSelection = SkysailApplication.applicationsActorSelection(system)
  //    val r = applicationsActorSelection.resolveOne(2.seconds)
  //    val appsActor = Await.result(r, 2.seconds)
  //    implicit val askTimeout: Timeout = 3.seconds
  //    (appsActor ? CreateApplicationActor(this.getClass)).mapTo[ActorRef]
  //  }

  val routes: List[Route] = {
    routesMappings.foreach(m => {
      log info s"mapping '${appModel.appPath()}/${m._1}' to '${m._2}'"
      appModel.addResourceModel(m._1, m._2)
    })

    val pathResourceTuple = appModel.getResourceModels().map {
      m => (m.pathMatcher, m.targetResourceClass)
    }
    pathResourceTuple.map { prt => createRoute2(prt._1, prt._2) }.toList
  }

  //val associatedResourceClasses = scala.collection.mutable.ListBuffer[Tuple2[ResourceAssociationType, Class[_ <: SkysailServerResource[_]]]]()

  var componentContext: ComponentContext = null
  def getComponentContext() = componentContext

  //  var applicationModel: ApplicationModel = null
  //  def getApplicationModel() = applicationModel

 // val repositories = new ArrayList[ScalaDbRepository]();

  var host = "localhost"
  def getHost = host

  var appRoutes: List[Route] = _
  var system: ActorSystem = _
  val cnt = new AtomicInteger(0)

  val stringContextMap = new java.util.HashMap[ApplicationContextId, String]()

  //setName(name);
  //getEncoderService().getIgnoredMediaTypes().add(SkysailApplication.SKYSAIL_SERVER_SENT_EVENTS);
  //getEncoderService().setEnabled(true);
  log.debug("Instanciating new Skysail ApplicationModel '{}'", this.getClass().getSimpleName());
  //applicationModel = ApplicationModel(name, apiVersion, associatedResourceClasses.toList)

  def getResourceBundles() = List[ResourceBundle]()

  def getTemplatePaths[T](x$1: Class[T]): java.util.List[String] = {
    Collections.emptyList()
  }

  @Activate
  def activate(componentContext: ComponentContext) = {
    this.componentContext = componentContext;
    val appsActor = SkysailApplication.getApplicationsActor(system)
    implicit val askTimeout: Timeout = 3.seconds
    appsActor ! CreateApplicationActor(this.getClass)
  }

  @Activate
  def activate(appConfig: ApplicationConfiguration, componentContext: ComponentContext): Unit = {
    activate(componentContext);
    host = appConfig.host();
  }

  @Deactivate
  def deactivate(componentContext: ComponentContext): Unit = {
    this.componentContext = null;
    val appsActor = SkysailApplication.getApplicationsActor(system)
    implicit val askTimeout: Timeout = 3.seconds
    appsActor ! DeleteApplicationActor(this.getClass)
  }

  def attach(): Unit = {
  }

  def getBundleContext(): Option[BundleContext] = {
    if (componentContext != null) {
      return Some(componentContext.getBundleContext())
    }
    None
  }

//  def addRepository(repository: ScalaDbRepository) = {
//    this.repositories.add(repository);
//  }

  def getSkysailApplication() = this

  def getBundle(): Bundle = {
    if (componentContext == null) {
      return null;
    }
    return componentContext.getBundleContext().getBundle();
  }

//  //Class<? extends Entity>
//  def getRepository[T <: ScalaDbRepository](entityClass: Class[_]): T = {
//    val repo = repositories.asScala.filter { r =>
//      val entityType = ScalaReflectionUtils.getParameterizedType(r.getClass())
//      entityClass.isAssignableFrom(entityType)
//    }.headOption
//      .getOrElse(
//        //log.warn("no matching repository found for '{}'", entityClass.getName())
//        //return new NoOpDbRepository[T]()
//        throw new RuntimeException("no repo"))
//    repo.asInstanceOf[T]
//  }

  protected def createRoute2(appPath: PathMatcher[Unit], cls: Class[_ <: ResourceActor[_]]) = {
    path(appPath) {
      get {
        extractRequestContext {
          ctx =>
            {
              implicit val askTimeout: Timeout = 3.seconds
             // implicit val executionContext = system.dispatcher

              val extracted = cls.getName + "-" + cnt.incrementAndGet()

              val res = new PrivateMethodExposer(system)('printTree)()
              println(res)

              //              val applicationsActorPath = "/user/" + classOf[ApplicationsActor].getSimpleName
              //              log info s"searching applicationsActor @ path '${applicationsActorPath}'"
              //              val applicationsActor = system.actorSelection(applicationsActorPath)

              val applicationsActor = getApplicationsActor(system)

              log info "applicationsActorSelection: " + applicationsActor
              // println("applicationActor:  " + this.applicationActor)

              //              println("hier: " + applicationsActor)
              //              applicationsActor.resolveOne(2.seconds).onComplete {
              //                aa => aa.get ! SkysailApplication.InitResourceActorChain(ctx, cls)
              //              }

              //              onSuccess(
              //              applicationsActor.resolveOne(2.seconds).onComplete {
              //                aa => val result = (aa.get ? SkysailApplication.InitResourceActorChain(ctx, cls)).mapTo[HttpResponse]
              //               // result.onSuccess(r => complete(r))
              //                //onSuccess(result => complete(result))
              //              }) {
              //                result => complete(result)
              //              }

              //              val pf: PartialFunction[ActorRef,Future[HttpResponse]] = {
              //                case aa: ActorRef => (aa ? SkysailApplication.InitResourceActorChain(ctx, cls)).mapTo[HttpResponse]
              //              }
              //              applicationsActor.resolveOne(2.seconds).onSuccess(pf)
              log info s"actorOf ${cls}"
              val actor = system.actorOf(Props.apply(cls), extracted)

              val appActorSelection = getApplicationActorSelection(system, this.getClass.getName)
              log info "appActorSelection: " + appActorSelection
//              val t = (actor ? ctx).mapTo[HttpResponse]
////              val q = t onComplete {
//////                case Success(s:Any) => println("success")
//////                case Failure(f) => println("failure")
////                e => println("hier: " + e)//; complete(e.get)
////              }
//              onSuccess(t) {
//                result => println("hier:" + result); complete(result)
//              }
//              val t = (actor ? ctx).mapTo[HttpResponse]
////              val q = t onComplete {
//////                case Success(s:Any) => println("success")
//////                case Failure(f) => println("failure")
////                e => println("hier: " + e)//; complete(e.get)
////              }
//              onSuccess(t) {
//                result => println("hier:" + result); complete(result)
//              }
//


              //val r = t.map(x => x.toString)
              //r.foreach( x => println("Hier: " + x))
//              onSuccess(t.mapTo[Any]) { result =>
//                log info "###1: "+result.toString()
//                val r = complete(result.asInstanceOf[akka.http.scaladsl.marshalling.ToResponseMarshallable])
//                //system.stop(actor)
//                r
//              }


              val t2 = (actor ? ctx)
              val p = onSuccess(t2.mapTo[HttpResponse]) { result =>
                log info "###2: "+result.toString()
                val r = complete(result)
                system.stop(actor)
                r
              }
              p
            }
        }
      }
    }
  }

  private def getResourceActor(cls: Class[_ <: ResourceActor[_]]) = actorRefsMap get cls.getName getOrElse {
    log info s"creating new actor for ${cls.getName}"
    val c = system.actorOf(Props.apply(cls), cls.getName)
    actorRefsMap += cls.getName -> c
    //system watch c
    c
  }

}

