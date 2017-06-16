package io.skysail.restlet.utils

import io.skysail.core.restlet.SkysailServerResource
import io.skysail.restlet.responses.ScalaSkysailResponse
import io.skysail.core.forms.ScalaFormField
import io.skysail.core.app.SkysailApplicationService
import io.skysail.core.restlet.FieldsFactory

object ScalaFormfieldUtils {
  def determineFormfields(resource: SkysailServerResource[_], appService: SkysailApplicationService): java.util.Map[String, ScalaFormField] = {
    null//FieldsFactory.getFactory(resource).determineFrom(resource, appService);
  }

  def determineFormfields(response: ScalaSkysailResponse[_], resource: SkysailServerResource[_]) = {
    val f = FieldsFactory.getFactory(response)
    f.determineFrom(resource);
  }

}