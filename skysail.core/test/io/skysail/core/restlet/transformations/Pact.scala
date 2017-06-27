package io.skysail.core.restlet.transformations

import javax.validation.constraints._
import com.fasterxml.jackson.annotation._
import scala.beans.BeanProperty
import io.skysail.core.html._
import io.skysail.core.domain.ScalaEntity
import io.skysail.core.forms._
import scala.annotation.meta.field

@JsonIgnoreProperties(ignoreUnknown = true)
case class Pact(
    var id: Option[String] = None,
    @BeanProperty @(Field @field) /*@(NotNull @field) @Size(min=1)*/ var title: String = "",
    @BeanProperty var turn: Turn = new Turn("test")) extends ScalaEntity[String] {

  // title, selectionStrategy & confirmationS

}