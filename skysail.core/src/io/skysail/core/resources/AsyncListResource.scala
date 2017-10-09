package io.skysail.core.resources

import io.skysail.core.akka.{RequestEvent, ListResponseEvent}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}

abstract class AsyncListResource[T: TypeTag] extends AsyncResource[List[T]] {

  def get(requestEvent: RequestEvent): Unit

  def reply(requestEvent: RequestEvent, answer: Future[List[_]]) = {
    answer.onComplete {
      case Success(s) => requestEvent.controllerActor ! ListResponseEvent(requestEvent, answer)
      case Failure(f) => println(s"failure ${f}")
    }
  }

  def reply[U](requestEvent: RequestEvent, answer: Future[List[U]], c: List[U] => List[T]) = {
    answer.onComplete {
      case Success(s) => requestEvent.controllerActor ! ListResponseEvent(requestEvent, c.apply(s))
      case Failure(f) => println(s"failure ${f}")
    }
  }


}