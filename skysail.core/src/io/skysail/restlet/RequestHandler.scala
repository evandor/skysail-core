package io.skysail.restlet

import org.restlet.representation.Variant
import io.skysail.core.restlet.filter._
import io.skysail.core.restlet.filter.AddLinkheadersFilter
import io.skysail.core.restlet.filter.PutRedirectGetFilter
import io.skysail.core.restlet.filter.FormDataExtractingFilter
import io.skysail.core.restlet.filter.DataExtractingFilter
import io.skysail.core.restlet.filter.CheckBusinessViolationsFilter
import io.skysail.core.restlet.filter.ExtractStandardQueryParametersResourceFilter
import io.skysail.core.restlet.filter.EntityWasAddedFilter
import io.skysail.core.restlet.filter.PersistEntityFilter
import io.skysail.core.restlet.filter.ExceptionCatchingFilter
import io.skysail.core.restlet.filter.CheckInvalidInputFilter
import io.skysail.core.restlet.filter.PostRedirectGetFilter
import io.skysail.core.restlet.filter.UpdateEntityFilter

class ScalaRequestHandler[T: Manifest](entity: T, variant: Variant) {

  def createForPost(): ScalaAbstractResourceFilter[T] = {

    new ExceptionCatchingFilter[T]()
      .calling(new ExtractStandardQueryParametersResourceFilter[T]())
      .calling(new CheckInvalidInputFilter[T](entity))
      .calling(new FormDataExtractingFilter[T](entity))
      .calling(new CheckBusinessViolationsFilter[T](entity))
      .calling(new PersistEntityFilter[T](entity))
      .calling(new EntityWasAddedFilter[T](entity))
      .calling(new AddLinkheadersFilter[T]())
      .calling(new PostRedirectGetFilter[T](variant))
      .asInstanceOf[ScalaAbstractResourceFilter[T]]
  }

  def createForPut(): ScalaAbstractResourceFilter[T] = {

    new ExceptionCatchingFilter[T]()
      .calling(new ExtractStandardQueryParametersResourceFilter[T]())
      .calling(new CheckInvalidInputFilter[T](entity))
      .calling(new FormDataExtractingFilter[T](entity))
      .calling(new CheckBusinessViolationsFilter[T](entity))
      .calling(new UpdateEntityFilter[T](entity))
      .calling(new EntityWasAddedFilter[T](entity))
      .calling(new AddLinkheadersFilter[T]())
      .calling(new PutRedirectGetFilter[T](variant))
      .asInstanceOf[ScalaAbstractResourceFilter[T]]
  }

  def createForGet(): ScalaAbstractResourceFilter[T] = {
    new ExceptionCatchingFilter[T]()
      .calling(new ExtractStandardQueryParametersResourceFilter[T]())
      .calling(new DataExtractingFilter[T]())
      //.calling(new AddReferrerCookieFilter[T]())
      .calling(new AddLinkheadersFilter[T]())
      .asInstanceOf[ScalaAbstractResourceFilter[T]]

  }

  def createForDelete(): ScalaAbstractResourceFilter[T] = {
    new ExceptionCatchingFilter[T]()
      .calling(new ExtractStandardQueryParametersResourceFilter[T]())
      .calling(new DeleteEntityFilter[T]())
      .calling(new EntityWasDeletedFilter[T]())
      .calling(new DeleteRedirectGetFilter2[T](variant))
      .asInstanceOf[ScalaAbstractResourceFilter[T]]
  }

}