package io.skysail.restlet.transformations

import javax.validation.constraints._
import com.fasterxml.jackson.annotation._
import java.util.Date

import scala.annotation.meta.field
import scala.beans.BeanProperty

import io.skysail.core.html._
import io.skysail.core.domain.ScalaEntity
import io.skysail.restlet.forms._

case class SimpleEntity (
    var id: Option[String] = None,
    @BeanProperty @(Field @field) var title: String = ""
  ) extends ScalaEntity[String] 

case class OuterEntity (
    var id: Option[String] = None,
    @BeanProperty @(Field @field) var title: String = "",
    @BeanProperty var turn:InnerEntity = new InnerEntity("test")
  ) extends ScalaEntity[String] 

case class InnerEntity (
    @BeanProperty @(Field @field) var nextTurn: String = "initial",
    @BeanProperty @(Field @field) var lastConfirmation: Option[String] = Some("")
  ) 