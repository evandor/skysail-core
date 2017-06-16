package io.skysail.restlet.services

trait ScalaSkysailComponentProvider {

    def getSkysailComponent(): io.skysail.core.restlet.ScalaSkysailComponent
}
