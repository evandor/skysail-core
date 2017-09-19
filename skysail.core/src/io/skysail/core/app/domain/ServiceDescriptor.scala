package io.skysail.core.app.domain

import org.osgi.framework.ServiceReference
import org.osgi.framework.Constants

object ServiceDescriptor {
  
  def apply(ref: ServiceReference[_]): ServiceDescriptor = {
    val objectClass = ref.getProperty(Constants.OBJECTCLASS).asInstanceOf[Array[String]].mkString(", ")
    ServiceDescriptor(
      ref.getProperty(Constants.SERVICE_ID).asInstanceOf[Long],
      objectClass,
      ref.getProperty(Constants.SERVICE_PID).asInstanceOf[String],
      if (ref.getProperty(Constants.SERVICE_RANKING) != null) ref.getProperty(Constants.SERVICE_RANKING).toString else "",
      ref.getBundle().getBundleId()
    )
  }
  
}

case class ServiceDescriptor(
  id: Long,
  objectClass: String,
  pid: String,
  ranking: String,
  bundleId: Long)

