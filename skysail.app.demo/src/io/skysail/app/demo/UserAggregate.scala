package io.skysail.app.demo

import akka.actor.{ActorLogging, ActorRef, Props, SupervisorStrategy}
import akka.event.LoggingReceive
import akka.persistence.{AtLeastOnceDelivery, PersistentActor}
import io.skysail.app.demo.EventSender.Confirm
import io.skysail.app.demo.UserAggregate.{Evt, GetUsersForwardResponse, MsgAddUser, MsgConfirmed}
import io.skysail.app.demo.UserRepository.ConfirmAddUser

object UserAggregate {

  final val Name = "user-aggregate"

  def props(): Props = Props(new UserAggregate())

  sealed trait Evt

  final case class MsgAddUser(u: Contact) extends Evt

  final case class MsgConfirmed(deliveryId: Long) extends Evt

  final case class GetUsersForwardResponse(senderActor: ActorRef, existingUsers: Set[Contact], newUser: Contact)
}

class UserAggregate extends PersistentActor with AtLeastOnceDelivery with ActorLogging {

  import akka.util.Timeout

  import scala.concurrent.duration._

  override val persistenceId: String = "user-aggregate"
  override val supervisorStrategy = SupervisorStrategy.stoppingStrategy
  implicit val timeout = Timeout(100 milliseconds)

  //private val userRepository = context.watch(createUserRepository())
  //private val eventSender = context.watch(createEventSender())

  protected def createUserRepository(): ActorRef = {
    context.actorOf(UserRepository.props(), UserRepository.Name)
  }

  protected def createEventSender(): ActorRef = {
    context.actorOf(EventSender.props(), EventSender.Name)
  }

  override def receiveCommand: Receive = LoggingReceive {
    /*
    Not the nicest solution, but it's non-blocking and sufficient to show the idea.
    Other solutions would be;
    - Have this UserAggregate also be the UserRepository, but that would mean mixing responsibilities
    - Use the pipe to self but change behaviour so that intermediate commands are re-queued
     */
    case AddUserCmd(newUser) =>
      val origSender = sender()
          println("AddUserCmd II")

      //val usersFuture = userRepository ? GetUsers
      //pipe(usersFuture.mapTo[Set[Contact]].map(GetUsersForwardResponse(origSender, _, newUser))) to self
      self ! GetUsersForwardResponse(origSender, Set(), newUser)
    case GetUsersForwardResponse(origSender, users, newUser) =>
      //if (users.exists(_.email == newUser.email)) {
//        origSender ! UserExistsResp(newUser)
//      } else {
        persist(MsgAddUser(newUser)) { persistedMsg =>
          //updateState(persistedMsg)
          origSender ! UserAddedResp(newUser)
        //}
      }

    case ConfirmAddUser(deliveryId) =>
      persist(MsgConfirmed(deliveryId))(updateState)
    case Confirm(deliveryId) =>
      persist(MsgConfirmed(deliveryId))(updateState)
  }

  override def receiveRecover: Receive = LoggingReceive {
    case evt: Evt => updateState(evt)
  }

  def updateState(evt: Evt): Unit = evt match {
    case MsgAddUser(u) =>
      //deliver(eventSender.path)(deliveryId => Msg(deliveryId, u))
      //deliver(userRepository.path)(deliveryId => AddUser(deliveryId, u))
    case MsgConfirmed(deliveryId) =>
      confirmDelivery(deliveryId)
  }

}
