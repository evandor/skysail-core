package io.skysail.domain.ddd

import scala.beans.BeanProperty

trait ScalaEntity[IdType] {
  var id:Option[IdType]
  def setId(id: IdType) = Some(id)
  def getId() = if (id.isDefined) id.get else null
}