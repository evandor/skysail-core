package io.skysail.restlet.responses

import org.restlet.Response

sealed trait ScalaSkysailResponse[T] {
  def entity(): T
  def isForm() = this.isInstanceOf[FormResponse[T]] // || this instanceof ConstraintViolationsResponse
  def isList() = this.isInstanceOf[ListResponse[T]]
}

final case class FormResponse[T](response: Response, entity: T, target: String) extends ScalaSkysailResponse[T]

final case class ListResponse[T](response: Response, entity: List[T]) extends ScalaSkysailResponse[List[T]]

final case class EntityResponse[T](response: Response, entity: T) extends ScalaSkysailResponse[T]
