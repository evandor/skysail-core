package io.skysail.core.resources

import io.skysail.core.akka._
import io.skysail.core.app.resources.ActorContextAware
import akka.actor.ActorRef

abstract class AsyncStaticResource extends AsyncResource[Any] {

  def get(sendBackTo: ActorRef): Unit

}