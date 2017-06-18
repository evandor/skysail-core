package io.skysail.core.restlet.responses

import org.restlet.Response
import org.restlet.data.Status

sealed trait ScalaSkysailResponse[T] {
  def entity(): T
  def status: Status
  def isForm() = this.isInstanceOf[FormResponse[T]] // || this instanceof ConstraintViolationsResponse
  def isList() = this.isInstanceOf[ListResponse[T]]
}

abstract case class AbstractSkysailResponse[T](response:Response) extends ScalaSkysailResponse[T] {
  def status() = response.getStatus
}

final class FormResponse[T](response: Response, entity: T, var target: String) extends AbstractSkysailResponse[T](response) {
  def entity(): T = entity
}

final class ListResponse[T](response: Response, entity: T) extends AbstractSkysailResponse[T](response) {
  def entity(): T = entity
}

final class EntityResponse[T](response: Response, entity: T) extends AbstractSkysailResponse[T](response) {
  def entity(): T = entity
  
}
