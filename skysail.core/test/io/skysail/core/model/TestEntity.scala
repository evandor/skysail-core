//package io.skysail.core.model
//
//import scala.annotation.meta.field
//import scala.beans.BeanProperty
//import io.skysail.core.domain.ScalaEntity
//import io.skysail.core.html.Field
//
//@BeanProperty
//case class TestEntity (
//    var id: Option[String],
//    @BeanProperty @(Field @field) var name: String,
//    @BeanProperty @(Field @field) var content: String
//  ) extends ScalaEntity[String] {
//}