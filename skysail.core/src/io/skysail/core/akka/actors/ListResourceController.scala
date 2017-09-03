package io.skysail.core.akka.actors

import io.skysail.core.akka._
import io.skysail.core.model.ApplicationModel
import scala.reflect.runtime.universe._
import scala.reflect.ClassTag
import io.skysail.core.resources.Resource

abstract class ListResourceController[T: ClassTag] extends Resource[List[T]] {

  def entityClass(): Class[_] = {
    val ctag = implicitly[reflect.ClassTag[T]]
    ctag.runtimeClass.asInstanceOf[Class[T]]
  }

}