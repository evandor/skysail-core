package io.skysail.core.akka

import akka.actor.Actor
import akka.actor.ActorLogging

case class Bid(userId: String, offer: Int)
case object GetBids
case class Bids(bids: List[Bid])

class Auction extends Actor with ActorLogging {
  var bids = List.empty[Bid]
  def receive = {
    case bid @ Bid(userId, offer) =>
      bids = bids :+ bid
      log.info(s"Bid complete: $userId, $offer")
    case GetBids => sender() ! Bids(bids)
    case _ => log.info("Invalid message")
  }
}