package io.skysail.core.app.resources

import io.skysail.core.akka.actors._


class DefaultResource2 extends ListResource[String] {  
  override def get(): List[String] = List("hi", "there")
}
