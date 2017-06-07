package io.skysail.core.domain

trait ScalaEntity[IdType] {
  var id:Option[IdType]
  def setId(id: IdType) = Some(id)
  def getId() = if (id.isDefined) id.get else null
}