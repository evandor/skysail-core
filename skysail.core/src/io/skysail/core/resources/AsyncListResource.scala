package io.skysail.core.resources

import io.skysail.core.akka.{RequestEvent, ResponseEvent}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}

abstract class AsyncListResource[T: TypeTag] extends AsyncResource[List[T]] {

  def get(requestEvent: RequestEvent): Unit

  def reply[U](requestEvent: RequestEvent, answer: Future[List[U]], c: List[U] => List[T]) = {
    answer.onComplete {
      case Success(s) => requestEvent.resourceActor ! ResponseEvent(requestEvent, c.apply(s))
      case Failure(f) => println(s"failure ${f}")
    }
  }


}