package io.skysail.app.demo

import io.skysail.core.akka.actors.ListResource

class AppsResource extends ListResource[Application] {
  override def get(): List[Application] = List(Application("hi"), Application("there"))
}