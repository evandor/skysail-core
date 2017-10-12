package io.skysail.api.ddd

trait DddElement

trait Entity[T] extends DddElement {
  def id: Option[T]

  override def hashCode(): Int = id.hashCode()

  override def equals(obj: scala.Any): Boolean = {
    if (!obj.isInstanceOf[Entity[T]]) {
      return false
    }
    val otherEntity = obj.asInstanceOf[Entity[T]]
    if (id == null || otherEntity.id == null) {
      return false
    }
    if (id == None || otherEntity.id == None) {
      return false
    }
    return otherEntity.id == id
  }
}

trait ValueObject extends DddElement