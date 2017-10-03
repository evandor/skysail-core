package io.skysail.core.app.resources

import akka.actor.ActorRef
import io.skysail.core.akka.RequestEvent
import io.skysail.core.resources.AsyncEntityResource
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

import scala.concurrent.Future
import scala.reflect.ClassTag

class AppResource extends AsyncEntityResource[String] {

  def get(requestEvent: RequestEvent): Unit = {
    ???
  }
}