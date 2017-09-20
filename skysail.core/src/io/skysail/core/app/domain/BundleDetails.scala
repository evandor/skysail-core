package io.skysail.core.app.domain

import org.osgi.framework.Bundle

case class BundleDetails(bundle:Bundle) {
  val descriptor= BundleDescriptor(bundle)
  val location = bundle.getLocation()
}