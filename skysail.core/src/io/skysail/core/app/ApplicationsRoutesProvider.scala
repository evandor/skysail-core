//package io.skysail.core.app
//
//import org.osgi.service.component.annotations._
//import scala.collection.JavaConverters._
//import org.restlet.Application
//import org.slf4j.LoggerFactory
//import java.text.DecimalFormat
//import scala.collection.mutable.ListBuffer
//import io.skysail.core.restlet.ScalaSkysailComponent
//import io.skysail.core.restlet.services.SkysailStatusService
//import io.skysail.core.restlet.services.ResourceBundleProvider
//import org.osgi.service.cm.ManagedService
//import akka.http.scaladsl.server.Route
//
//
//object ApplicationsRoutesProvider {
////  def getApplication(provider: ApplicationProvider): SkysailApplication = {
////    val application = provider.getSkysailApplication();
////    require(application != null, "application from applicationProvider must not be null");
////    return application;
////  }
////  def formatSize(list: ListBuffer[_]) = new DecimalFormat("00").format(list.length)
//}
//
//@Component(immediate = true, service = Array(classOf[ApplicationsRoutesProvider]))
//class ApplicationsRoutesProvider {
//
//	val log = LoggerFactory.getLogger(this.getClass)
//  val routes = scala.collection.mutable.ListBuffer[Route]()
//  
//  @Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.MULTIPLE)
//  def addApplicationRoutes(provider: ApplicationRoutesProvider): Unit = {
//    routes ++= provider.routes
//    //log.info("(+ ApplicationModel) (#{}) with name '{}'", ApplicationList.formatSize(applications), application.getName(): Any);
//  }
//
//  def removeApplicationRoutes(provider: ApplicationRoutesProvider): Unit = {
//    routes --= provider.routes
//    //log.info("(- ApplicationModel) name '{}', count is {} now", application.getName(), ApplicationList.formatSize(applications): Any);
//  }
//
// 
//
//
//}
