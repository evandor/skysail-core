package io.skysail.core.app.resources

import io.skysail.core.akka._
import io.skysail.core.akka.ResourceController
import io.skysail.core.akka.actors.Redirector
import io.skysail.core.akka.actors.Timer
import io.skysail.core.dsl.ActorChainDsl._
import scala.reflect.ClassTag

abstract class RedirectResource[String:ClassTag] extends ResourceController[String] {

  val ctag = implicitly[reflect.ClassTag[String]]
  val xxx = ctag.runtimeClass.asInstanceOf[Class[String]]

  override val chainRoot = (
    classOf[RequestProcessingActor[_]] ==>
    classOf[Timer] ==>
    classOf[Redirector]).build(xxx)

  def redirectTo(): String

}