package io.skysail.app.demo

import akka.actor.{ Actor, ActorLogging, ActorPath, Props, Status }
import akka.camel.{ CamelMessage, Producer }
//import nl.codecentric.coffee.ActorSettings
//import nl.codecentric.coffee.domain.User
import org.apache.camel.component.rabbitmq.RabbitMQConstants

import scala.collection.immutable

object EventSender {

  final val Name = "event-sender"

  def props(): Props = Props(new EventSender())

  final case class Msg(deliveryId: Long, user: Contact)

  final case class Confirm(deliveryId: Long)
}

class EventSender extends Actor with ActorLogging {
  import EventSender._
//  import io.circe.generic.auto._
//  import io.circe.syntax._

  private val camelSender = context.watch(context.actorOf(Props[CamelSender]))

  private var unconfirmed = immutable.SortedMap.empty[Long, ActorPath]

  override def receive: Receive = {
    case Msg(deliveryId, user) =>
      log.info("Sending msg for user: {}", user.email)
      unconfirmed = unconfirmed.updated(deliveryId, sender().path)
//      val headersMap = Map(RabbitMQConstants.MESSAGE_ID -> deliveryId, RabbitMQConstants.CORRELATIONID -> deliveryId)
//      camelSender ! CamelMessage(user.asJson.noSpaces, headersMap)

    case CamelMessage(_, headers) =>
      val deliveryId: Long = headers.getOrElse(RabbitMQConstants.MESSAGE_ID, -1L).asInstanceOf[Long]
      log.info("Event successfully delivered for id {}, sending confirmation", deliveryId)
      unconfirmed
        .get(deliveryId)
        .foreach(
          senderActor => {
            unconfirmed -= deliveryId
            context.actorSelection(senderActor) ! Confirm(deliveryId)
          }
        )

    case Status.Failure(ex) =>
      log.error("Event delivery failed. Reason: {}", ex.toString)
  }
}

class CamelSender extends Actor with Producer /*with ActorSettings*/ {
  override def endpointUri: String = "rabbitmq://127.0.0.1:5672/userevents?username=guest&password=guest"//settings.rabbitMQ.uri

  override def headersToCopy: Set[String] =
    super.headersToCopy + RabbitMQConstants.CORRELATIONID + RabbitMQConstants.MESSAGE_ID
}
