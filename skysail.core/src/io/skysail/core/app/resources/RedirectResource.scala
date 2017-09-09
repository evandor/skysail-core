package io.skysail.core.app.resources

import io.skysail.core.akka._
import scala.reflect.ClassTag
import io.skysail.core.resources.Resource
import scala.reflect.runtime.universe._

abstract class RedirectResource[String:TypeTag] extends Resource[String] {

  def redirectTo(): String

}