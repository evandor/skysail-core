package io.skysail.core.app.domain

import org.osgi.framework.ServiceReference

object ServiceReferenceDescriptor {
 def apply(ref: ServiceReference[_]) = {
   
   var id = "theid"
   
//   ref.getPropertyKeys.foreach(key => {
//     case "service.id" => id = ref.getProperty("service.id").toString
//     //case "objectClass" => if (ref.getProperty("objectClass") != null) ref.getProperty("objectClass").toString()
//   })
   new ServiceReferenceDescriptor(id)
 }
}

case class ServiceReferenceDescriptor(id: String) {
          
        
//                Arrays.stream(serviceRef.getPropertyKeys()).forEach(key -> {
//            if ("service.id".equals(key)) {
//                this.id = serviceRef.getProperty(key).toString();
//            } else if ("objectClass".equals(key) && serviceRef.getProperty(key) != null) {
//                this.objectClass = Arrays.stream((String[]) serviceRef.getProperty(key))
//                        .collect(Collectors.joining(","));
//            } else if ("service.bundleid".equals(key)) {
//                this.bundleId = serviceRef.getProperty(key).toString();
//            } else {
//                properties.put(key, serviceRef.getProperty(key).toString());
//            }
//        });

  
}