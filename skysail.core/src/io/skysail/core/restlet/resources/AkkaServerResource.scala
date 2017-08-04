//package io.skysail.core.restlet.resources
//
//import akka.actor.ActorSystem
//import akka.http.scaladsl.Http
//import akka.stream.ActorMaterializer
//import akka.Done
//import akka.http.scaladsl.server.Route
//import akka.http.scaladsl.server.Directives._
//import akka.http.scaladsl.model.StatusCodes
//import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
//import spray.json.DefaultJsonProtocol._
//
//import scala.io.StdIn
//
//import scala.concurrent.Future
//import scala.concurrent.ExecutionContext
//import akka.actor.Props
//import akka.util.Timeout
//import scala.concurrent.duration._
//import akka.pattern.ask
//import akka.actor.Actor
//import akka.actor.ActorLogging
//import java.util.Date
//
//case class Bid(userId: String, offer: Int) { }
//case object GetBids
//case class Bids(bids: List[Bid])
//
//class Auction extends Actor with ActorLogging {
//  var bids = List.empty[Bid]
//  def receive = {
//    case bid @ Bid(userId, offer) =>
//      bids = bids :+ bid
//      log.info(s"Bid complete: $userId, $offer")
//    case GetBids => sender() ! Bids(bids)
//    case _ => log.info("Invalid message")
//  }
//}
//
////class ListRequestHandler extends Actor with ActorLogging {
////  var bids = List.empty[Bid]
////  def receive = {
////    case bid @ Bid(userId, offer) =>
////      bids = bids :+ bid
////      log.info(s"Bid complete: $userId, $offer")
////    case GetBids => sender() ! Bids(bids)
////    case _ => log.info("Invalid message")
////  }
////}
//
////class AkkaRouteProvider(routePath: String) {
////
////  implicit val bidFormat = jsonFormat2(Bid)
////  implicit val bidsFormat = jsonFormat1(Bids)
////
////  def getRoute() =
////    path(routePath) {
////
////      get {
////        implicit val timeout: Timeout = 5.seconds
////
////        // query the actor for the current auction state
////        val bids = List[Bid]() //Future[Bids] = (auction ? GetBids).mapTo[Bids]
////        complete(bids)
////      }
////    }
////}