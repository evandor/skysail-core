package io.skysail.core.restlet.transformations

import javax.validation.constraints._
import com.fasterxml.jackson.annotation._
import scala.beans.BeanProperty
import io.skysail.core.html._
import io.skysail.core.forms._
import scala.annotation.meta.field

case class Turn (
    @BeanProperty @(Field @field) var nextTurn: String = "initial",
    @BeanProperty @(Field @field) var lastConfirmation: Option[String] = Some("")
  ) 