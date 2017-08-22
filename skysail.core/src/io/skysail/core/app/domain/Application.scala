package io.skysail.core.app.domain

import io.skysail.core.model.ApplicationModel

object Application {
  def apply(appModel: ApplicationModel) = {
    new Application(appModel.name, appModel.appPath(), appModel.description)
  }
}

case class Application(name: String, context: String, description: String)
