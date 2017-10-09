package io.skysail.core.resources

import io.skysail.core.akka._

import scala.concurrent.Future
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

abstract class AsyncEntityResource[T: TypeTag] extends AsyncResource[T] {
  
  def get(requestEvent: RequestEvent): Unit

  def reply[U](requestEvent: RequestEvent, answer: Future[U], c: U => T) = {
    answer.onComplete {
      case Success(s) => requestEvent.controllerActor ! ListResponseEvent(requestEvent, c.apply(s))
      case Failure(f) => println(s"failure ${f}")
    }
  }

}