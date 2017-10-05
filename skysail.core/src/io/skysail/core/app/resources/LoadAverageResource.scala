package io.skysail.core.app.resources

import io.skysail.core.akka.RequestEvent
import io.skysail.core.app.domain.{AvergeLoad, User}
import io.skysail.core.resources.AsyncEntityResource
import java.lang.management.ManagementFactory

class LoadAverageResource extends AsyncEntityResource[AvergeLoad] {

  val operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean
  
  override def get(requestEvent: RequestEvent) {
    requestEvent.resourceActor ! AvergeLoad(operatingSystemMXBean.getSystemLoadAverage())
  }


 
}