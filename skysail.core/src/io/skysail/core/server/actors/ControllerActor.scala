package io.skysail.core.akka

import akka.actor.{Actor, ActorLogging, ActorRef}
import akka.event.LoggingReceive
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.MediaTypeNegotiator
import akka.util.Timeout
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import io.skysail.core.app.resources.PostSupport
import io.skysail.core.model.{ApplicationModel, RepresentationModel}
import io.skysail.core.resources._
import io.skysail.core.server.actors.ApplicationActor.{ProcessCommand, SkysailContext}
import org.json4s.jackson.Serialization.write
import org.json4s.{DefaultFormats, Extraction, JObject, jackson}
import org.osgi.framework.BundleContext
import play.twirl.api.HtmlFormat

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

object ControllerActor {

  case class GetRequest()

  case class PostRequest()

  case class PutRequest()

  case class DeleteRequest()

  case class MyResponseEntity(val entity: ResponseEntity)

}

class ControllerActor[T]() extends Actor with ActorLogging {

  implicit val askTimeout: Timeout = 1.seconds

  var applicationActor: ActorRef = null

  import context._

  def receive = in

  def in: Receive = LoggingReceive {
    case SkysailContext(cmd: ProcessCommand, model: ApplicationModel, resource: AsyncResource[T], _: Option[BundleContext]) => {
      applicationActor = sender
      resource.setActorContext(context)
      resource.setApplicationModel(model)
      cmd.ctx.request.method match {
        case HttpMethods.GET => resource.get(RequestEvent(cmd, self))
        case HttpMethods.POST => resource.asInstanceOf[PostSupport].post(RequestEvent(cmd, self))
        case e: Any => resource.get(RequestEvent(cmd, self))
      }
      become(out)
    }
    case msg: Any => log info s"<<< IN <<<: received unknown message '$msg' in ${this.getClass.getName}"
  }

  def out: Receive = LoggingReceive {
    case response: ListResponseEvent[T] =>
      val negotiator = new MediaTypeNegotiator(response.req.cmd.ctx.request.headers)
      val acceptedMediaRanges = negotiator.acceptedMediaRanges

      implicit val formats = DefaultFormats
      implicit val serialization = jackson.Serialization

      val m = Marshal(response.resource.asInstanceOf[List[_]]).to[RequestEntity]

      if (negotiator.isAccepted(MediaTypes.`text/html`)) {
        handleHtmlWithFallback(response, m)
      } else if (negotiator.isAccepted(MediaTypes.`application/json`)) {
        handleJson(m, response)
      }
    case response: ResponseEvent[T] =>
      val negotiator = new MediaTypeNegotiator(response.req.cmd.ctx.request.headers)
      val acceptedMediaRanges = negotiator.acceptedMediaRanges

      implicit val formats = DefaultFormats
      implicit val serialization = jackson.Serialization

      val e = Extraction.decompose(response.resource).asInstanceOf[JObject]
      val written = write(e)

      if (negotiator.isAccepted(MediaTypes.`text/html`)) {
        handleHtmlWithFallback(response, e)
      } else if (negotiator.isAccepted(MediaTypes.`application/json`)) {
        //handleJson(m,response)
      }
    case msg: List[T] => {
      log warning s">>> OUT(${this.hashCode()}) @deprecated >>>: List[T]"
      implicit val formats = DefaultFormats
      implicit val serialization = jackson.Serialization
      val m = Marshal(msg).to[RequestEntity]
      m.onSuccess {
        case value =>
          val reqEvent = RequestEvent(null, null)
          val resEvent = ListResponseEvent(reqEvent, null)
          log info s">>> OUT(${this.hashCode()} >>>: sending back to ${applicationActor}"
          applicationActor ! resEvent.copy(resource = msg, httpResponse = resEvent.httpResponse.copy(entity = value))
      }
    }
    case msg: ControllerActor.MyResponseEntity => {
      log warning s">>> OUT(${this.hashCode()}) @deprecated >>>: ControllerActor.MyResponseEntity"
      val reqEvent = RequestEvent(null, null)
      val resEvent = ListResponseEvent(reqEvent, null)
      applicationActor ! resEvent.copy(httpResponse = resEvent.httpResponse.copy(entity = msg.entity))
    }
    case msg: T => {
      log warning s">>> OUT(${this.hashCode()}) @deprecated >>>: T"
      val reqEvent = RequestEvent(null, null)
      val resEvent = ListResponseEvent(reqEvent, null)
      implicit val formats = DefaultFormats
      val e = Extraction.decompose(msg).asInstanceOf[JObject]
      val written = write(e)
      val r = HttpEntity(ContentTypes.`application/json`, written)
      applicationActor ! resEvent.copy(resource = msg, httpResponse = resEvent.httpResponse.copy(entity = r))
    }
    case msg: Any => log info s">>> OUT >>>: received unknown message '$msg' in ${this.getClass.getName}"
  }

  private def handleHtmlWithFallback(response: ListResponseEvent[T], m: Future[MessageEntity]) = {
    try {
      val loader = response.req.cmd.cls.getClassLoader
      val resourceHtmlClass = loader.loadClass(getHtmlTemplate(response.req))
      val applyMethod = resourceHtmlClass.getMethod("apply", classOf[RepresentationModel])

      m.onSuccess {
        case value =>
          val rep = new RepresentationModel(response.resource)
          val r2 = applyMethod.invoke(resourceHtmlClass, rep).asInstanceOf[HtmlFormat.Appendable]
          val answer = HttpEntity(ContentTypes.`text/html(UTF-8)`, r2.body)
          applicationActor ! response.copy(resource = response.resource, httpResponse = response.httpResponse.copy(entity = answer))
      }

    } catch {
      case e: Exception => log info s"rendering fallback to json, could not load '${getHtmlTemplate(response.req)}', reason: $e"; handleJson(m, response)
    }
  }

  def handleHtmlWithFallback(response: ResponseEvent[T], e: JObject): Unit = {
    val resourceClassAsString = getHtmlTemplate(response.req)
    try {
      val loader = response.req.cmd.cls.getClassLoader
      val resourceHtmlClass = loader.loadClass(resourceClassAsString)
      val applyMethod = resourceHtmlClass.getMethod("apply", classOf[RepresentationModel])

      val rep = new RepresentationModel(response.resource)
      val r2 = applyMethod.invoke(resourceHtmlClass, rep).asInstanceOf[HtmlFormat.Appendable]
      val answer = HttpEntity(ContentTypes.`text/html(UTF-8)`, r2.body)
      applicationActor ! response.copy(resource = response.resource, httpResponse = response.httpResponse.copy(entity = answer))

    } catch {
      case e: Exception => log info s"rendering fallback to json, could not load '$resourceClassAsString', reason: $e" //; handleJson(m, response)
    }

  }


  private def handleJson(m: Future[MessageEntity], response: ListResponseEvent[T]) = {
    m.onSuccess {
      case value =>
        applicationActor ! response.copy(resource = response.resource, httpResponse = response.httpResponse.copy(entity = value))
    }
  }

  override def preRestart(reason: Throwable, message: Option[Any]) {
    log.error(reason, "Restarting due to [{}] when processing [{}]", reason.getMessage, message.getOrElse(""))
  }

  private def getHtmlTemplate(req: RequestEvent) = {
    s"${req.cmd.cls.getPackage.getName}.html.${req.cmd.cls.getSimpleName}_Get"
  }

}
