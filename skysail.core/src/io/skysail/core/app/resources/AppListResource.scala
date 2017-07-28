package io.skysail.core.app.resources

import io.skysail.core.akka._
import io.skysail.core.akka.actors.ListResource

class AppListResource extends ListResource[String] {
  override def get(): List[String] = List("hi","there")
}