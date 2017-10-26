package io.skysail.core.resources

import io.skysail.core.akka.RequestEvent
import io.skysail.core.app.resources.{PostSupport, PutSupport}

import scala.reflect.runtime.universe._

abstract class AsyncPutResource[T: TypeTag] extends AsyncResource[T] with PutSupport {

  def get(requestEvent: RequestEvent): Unit

  def put(requestEvent: RequestEvent): Unit

}