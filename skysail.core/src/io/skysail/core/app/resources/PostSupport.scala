package io.skysail.core.app.resources

import io.skysail.core.akka.RequestEvent

trait PostSupport {
  def post(requestEvent: RequestEvent): Unit
}
