package io.skysail.core.app.resources

import io.skysail.core.akka.actors.EntityResource

class AppResource extends EntityResource[String] {

  override def get() = { "Gi".asInstanceOf[String] }
}