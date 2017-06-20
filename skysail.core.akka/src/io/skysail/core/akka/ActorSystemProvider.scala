//package io.skysail.core.akka
//
//import org.osgi.service.component.annotations.Component
////import akka.actor.ActorSystem
//import scala.concurrent.Future
////import akka.http.scaladsl.Http
////import akka.http.scaladsl.Http.ServerBinding
////import akka.util.Timeout
////import akka.event.Logging
//import scala.concurrent.duration.FiniteDuration
//import scala.concurrent.duration.Duration
//
//@Component(service = Array(classOf[ActorSystemProvider]))
//class ActorSystemProvider {
//  
//  implicit val system = ActorSystem() 
//  implicit val ec = system.dispatcher
//  
////  val api = new RestApi(system, requestTimeout()).routes
////  
////  val bindingFuture: Future[ServerBinding] = Http().bindAndHandle(api, "localhost", 5000) //Starts the HTTP server
//// 
////  val log =  Logging(system.eventStream, "go-ticks")
////  bindingFuture.map { serverBinding =>
////    log.info(s"RestApi bound to ${serverBinding.localAddress} ")
////  }.onFailure { 
////    case ex: Exception =>
////      log.error(ex, "Failed to bind to {}:{}!", "localhost", 5000)
////      system.terminate()
////  }
////  
////  def requestTimeout(): Timeout = {
////    val t = "1000"//config.getString("akka.http.server.request-timeout")
////    val d = Duration(t)
////    FiniteDuration(d.length, d.unit)
////  }
//}
//
//trait RequestTimeout {
//  import scala.concurrent.duration._
//  def requestTimeout(): Timeout = {
//    val t = "1000"//config.getString("akka.http.server.request-timeout")
//    val d = Duration(t)
//    FiniteDuration(d.length, d.unit)
//  }
//}