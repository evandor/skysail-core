package io.skysail.core.restlet

import org.restlet.Response

class ListResponseWrapper[T](response: Response) extends Wrapper3 {

  var entity: List[T] = List()
  
  def getEntity() = entity

  def getResponse(): Response = response

  def setEntity(entity: List[T]): Unit = {
    this.entity = entity
  }
}