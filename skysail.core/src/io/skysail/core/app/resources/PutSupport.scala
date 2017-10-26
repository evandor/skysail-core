package io.skysail.core.app.resources

import io.skysail.core.akka.RequestEvent

trait PutSupport {
  def put(requestEvent: RequestEvent): Unit
}
