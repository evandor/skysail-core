package io.skysail.core.model

import io.skysail.core.akka.ResponseEvent
import io.skysail.core.resources.Resource

class RepresentationModel[T](responseEvent: ResponseEvent[T]) {

  val rawData = deriveRawData()

  def deriveRawData() = {

  }


}
