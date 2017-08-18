package io.skysail.core.app.domain

import org.osgi.framework.Bundle

object BundleDescriptor {
  def apply(b: Bundle): BundleDescriptor = {
    BundleDescriptor(
      b.getBundleId,
      b.getSymbolicName,
      if (b.getVersion != null) b.getVersion.toString() else "0.0.0",
      BundleDescriptor.getRegisteredServices(b),
      BundleDescriptor.getServicesInUse(b),
      b.getState)
  }
  private def getRegisteredServices(b: Bundle): List[ServiceReferenceDescriptor] = {
    val rs = b.getRegisteredServices
    if (rs == null) List() else rs.map(ref => ServiceReferenceDescriptor(ref)).toList
  }
  private def getServicesInUse(b: Bundle) = {
    val siu = b.getServicesInUse
    if (siu == null) List() else siu.map(ref => ServiceReferenceDescriptor(ref)).toList
  }
}

case class BundleDescriptor(
  id: Long,
  symbolicName: String,
  version: String,
  registeredServiceIds: List[ServiceReferenceDescriptor],
  servicesInUse: List[ServiceReferenceDescriptor],
  state: Int)

