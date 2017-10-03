package io.skysail.core.resources

import io.skysail.core.akka._
import akka.actor.ActorRef
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

abstract class AsyncStaticResource extends AsyncResource[Any] {

  def get(requestEvent: RequestEvent): Unit

}