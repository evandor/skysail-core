package io.skysail.core.akka

import akka.actor.{Actor, ActorLogging, ActorRef}
import akka.event.LoggingReceive
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.util.Timeout
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import io.skysail.core.app.resources.PostSupport
import io.skysail.core.model.ApplicationModel
import io.skysail.core.resources._
import io.skysail.core.server.actors.ApplicationActor.{ProcessCommand, SkysailContext}
import org.json4s.jackson.Serialization.write
import org.json4s.{DefaultFormats, Extraction, JObject, jackson}
import org.osgi.framework.BundleContext

import scala.concurrent.duration.DurationInt

object ControllerActor {
  case class GetRequest()
  case class PostRequest()
  case class PutRequest()
  case class DeleteRequest()
  case class MyResponseEntity(val entity: ResponseEntity)
}

class ControllerActor[T]( /*resource: Resource[_]*/ ) extends Actor with ActorLogging {

  implicit val askTimeout: Timeout = 1.seconds

  val originalSender = sender
  var sendBackTo: ActorRef = null

  import context._

  def receive = in

  def in: Receive = LoggingReceive {
    case SkysailContext(cmd: ProcessCommand, model: ApplicationModel, resource: AsyncResource[T], _: Option[BundleContext]) => {
      sendBackTo = sender
      log info s"<<< IN(${this.hashCode()}) <<<: SkysailContext"
      resource.setActorContext(context)
      resource.setApplicationModel(model)
      cmd.ctx.request.method match {
        case HttpMethods.POST => resource.asInstanceOf[PostSupport].post(self)
        case e: Any => resource.get(self,cmd)
      }
      become(out)
    }
    case msg: Any => log info s"<<< IN <<<: received unknown message '$msg' in ${this.getClass.getName}"
  }

  def out: Receive = LoggingReceive {
    case msg: List[T] => {
      log info s">>> OUT(${this.hashCode()} >>>: List[T]"
      //implicit val ec = context.system.dispatcher
      implicit val formats = DefaultFormats
      implicit val serialization = jackson.Serialization
      val m = Marshal(msg).to[RequestEntity]
      m.onSuccess {
        case value =>
          val reqEvent = RequestEvent(null, null)
          val resEvent = ResponseEvent(reqEvent, null)
          log info s">>> OUT(${this.hashCode()} >>>: sending back to ${sendBackTo}"
          sendBackTo ! resEvent.copy(resource = msg, httpResponse = resEvent.httpResponse.copy(entity = value))
      }
    }
    case msg: ControllerActor.MyResponseEntity => {
      log info s">>> OUT(${this.hashCode()} >>>: ControllerActor.MyResponseEntity"
      val reqEvent = RequestEvent(null, null)
      val resEvent = ResponseEvent(reqEvent, null)
      sendBackTo ! resEvent.copy(httpResponse = resEvent.httpResponse.copy(entity = msg.entity))
    }
    case msg: T => { /* and EntityDescription */
      log info s">>> OUT(${this.hashCode()}) >>>: T"
      val reqEvent = RequestEvent(null, null)
      val resEvent = ResponseEvent(reqEvent, null)
      implicit val formats = DefaultFormats
//      implicit val serialization = jackson.Serialization
//      val m = Marshal(List(msg)).to[RequestEntity]

      val e = Extraction.decompose(msg).asInstanceOf[JObject]
      val written = write(e)
      val r = HttpEntity(ContentTypes.`application/json`, written)

      sendBackTo ! resEvent.copy(resource = msg, httpResponse = resEvent.httpResponse.copy(entity = r))

//       m onComplete {
//        case Success(value) =>
//          val reqEvent = RequestEvent(null, null)
//          val resEvent = ResponseEvent(reqEvent, null)
//          sendBackTo ! resEvent.copy(resource = msg, httpResponse = resEvent.httpResponse.copy(entity = r))
//        case Failure(failure) => println (s"Failure: ${failure}")
//      }

    }
    case msg: Any => log info s">>> OUT >>>: received unknown message '$msg' in ${this.getClass.getName}"
  }

  override def preRestart(reason: Throwable, message: Option[Any]) {
    log.error(reason, "Restarting due to [{}] when processing [{}]", reason.getMessage, message.getOrElse(""))
  }

//  override val supervisorStrategy =
//    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
//      case _: ArithmeticException      => log warning "HIER: RESUMING"; Resume
//      case _: NullPointerException     => log warning "HIER: RESTART"; Restart
//      case _: IllegalArgumentException => log warning "HIER: STOPPIG"; Stop
//      case _: Exception                => log warning "HIER: EACALATE"; Escalate
//      case _: scala.NotImplementedError => log warning "HIER: EACALATE"; Escalate
//      case _: Any => log warning "HIER: XXX"; Resume
//    }

}
