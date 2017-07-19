package io.skysail.core.app.resources

import io.skysail.core.restlet.SkysailServerResource
import io.skysail.core.restlet.resources.RedirectResourceOld
import io.skysail.core.um.domain.Credentials

class LoginResource extends RedirectResourceOld[Credentials] {
  def getEntity(): Credentials = null

  def redirectToResource(): SkysailServerResource[_] = {
    null
  }

  override def redirectTo(): String = {
    val app = getSkysailApplication()
    if (app.isAuthenticated(getRequest())) {
      return "/";
    }
    return app.getSkysailApplication().getAuthenticationService().getLoginPath();
  }

}