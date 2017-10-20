package io.skysail.app.osgi.domain

import org.osgi.framework.ServiceReference

object ServiceReferenceDescriptor {

  private final val OBJECT_CLASS = "objectClass"
  private final val SERVICE_ID = "service.id"
  private final val BUNDLE_ID = "service.bundleid"

  def apply(ref: ServiceReference[_]): ServiceReferenceDescriptor = {

    var id, bundleId, objectClass = ""
    val properties = scala.collection.mutable.Map[String, String]()

    ref.getPropertyKeys.foreach(key => {
      key match {
        case SERVICE_ID => id = ref.getProperty(SERVICE_ID).toString
        case BUNDLE_ID => bundleId = ref.getProperty(BUNDLE_ID).toString
        case OBJECT_CLASS => if (ref.getProperty(OBJECT_CLASS) != null) {
          objectClass = ref.getProperty(OBJECT_CLASS).asInstanceOf[Array[String]].mkString(",")
        }
        case key: Any => properties.put(key, ref.getProperty(key).toString());
      }
    })
    new ServiceReferenceDescriptor(id, objectClass, bundleId, properties.toMap)
  }
}

case class ServiceReferenceDescriptor(id: String, objectClass: String, bundleId: String, properties: Map[String, String])