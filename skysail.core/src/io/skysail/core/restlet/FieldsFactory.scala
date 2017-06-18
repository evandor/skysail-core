package io.skysail.core.restlet

import io.skysail.core.restlet.responses.ScalaSkysailResponse
import io.skysail.core.restlet.responses.FormResponse

object FieldsFactory {
  def getFactory(response: ScalaSkysailResponse[_]): FieldFactory = {
    if (response.entity == null) {
      return new NoFieldFactory();
    }
    if (response.entity.isInstanceOf[List[_]]) {
      return new DefaultListFieldFactory();
      //    } else if (response instanceof ConstraintViolationsResponse) {
      //      return entityFactory((ConstraintViolationsResponse<?>) response);
    } else if (response.isInstanceOf[FormResponse[_]]) {
      return entityFactoryForForm(response.asInstanceOf[FormResponse[_]]);
    } else {
      new DefaultEntityFieldFactory(response.entity.getClass());
    }
  }

  private def entityFactoryForForm(source: FormResponse[_]):FieldFactory = {
    new FormResponseEntityFieldFactory(source.entity.getClass());
  }
}