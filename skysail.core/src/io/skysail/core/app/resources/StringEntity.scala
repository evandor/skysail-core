package io.skysail.core.app.resources

import io.skysail.domain.ddd.ScalaEntity
import scala.annotation.meta.field
import scala.beans.BeanProperty
import io.skysail.restlet.forms._
import io.skysail.core.html._

case class StringEntity (
    var id: Option[String] = None,
    @BeanProperty @(Field @field) var str: String = ""
  ) extends ScalaEntity[String] {}