package io.skysail.app.wyt;

import io.skysail.core.akka.actors._

class PactsResource extends ListResource[Pact] {
  val pactsService = new PactsService()
  override def get(): List[Pact] = pactsService.getPacts().toList
}

