package io.skysail.core.app.resources

import io.skysail.restlet.resources.EntityServerResource

class ModelResource extends EntityServerResource[StringEntity] {

	override def getEntity() = new StringEntity(None,getModel().toHtml(getRequest()))

}
