package io.skysail.core.restlet

import org.restlet.Response

class ScalaResponseWrapper[T](response: Response) extends Wrapper3 {

  var entity: Any = null
  
  def getEntity() = entity

  def getResponse(): Response = response

  def setEntity(entity: Any): Unit = {
    this.entity = entity
  }
}