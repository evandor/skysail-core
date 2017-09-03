package io.skysail.core.app.resources

import io.skysail.core.akka._
import scala.reflect.ClassTag
import io.skysail.core.resources.Resource

abstract class RedirectResource[String:ClassTag] extends Resource[String] {

  def redirectTo(): String

}