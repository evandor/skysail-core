package io.skysail.core.app.domain

import org.osgi.framework.Bundle

//object BundleDescriptor {
//  
//}

case class BundleDescriptor(id: Long, symbolicName: String, version: String, state: Int) {

  def this(b: Bundle) = {
    this(
      b.getBundleId,
      b.getSymbolicName,
      if (b.getVersion != null) b.getVersion.toString() else "0.0.0",
      b.getState)
  }

  private def getRegisteredServices(b: Bundle): List[ServiceReferenceDescriptor] = {
    if (b.getRegisteredServices() == null) {
      List()
    } else {
      b.getRegisteredServices().map(ref => ServiceReferenceDescriptor(ref)).toList
    }
  }

}


