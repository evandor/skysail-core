package io.skysail.app.osgi.domain

import org.osgi.framework.startlevel.BundleStartLevel
import org.osgi.framework.{Bundle, Constants}

object BundleDetails {


  def apply(bundle: Bundle) = {

    val headers = bundle.getHeaders(null)

    new BundleDetails(
      BundleDescriptor(bundle),
      bundle.getLocation,
      bundle.getLastModified,
      bundle.adapt(classOf[BundleStartLevel]).getStartLevel,
      //new WireDescriptor(bundle.adapt(classOf[BundleWiring])
      headers.get(Constants.BUNDLE_DOCURL),
      headers.get(Constants.BUNDLE_VENDOR),
      headers.get(Constants.BUNDLE_COPYRIGHT),
      headers.get(Constants.BUNDLE_DESCRIPTION),
      headers.get(Constants.BUNDLE_CLASSPATH),
      //      this.exportPackage = getExportedPackages(bundle, headers)
      //      this.importPackage = getImportedPackages(headers)
      dump(headers)
    )
  }

  //  def getHeaderInfo(bundle: Bundle) = {
  //      this.exportPackage = getExportedPackages(bundle, headers)
  //      this.importPackage = getImportedPackages(headers)
  //      this.manifestHeaders = dump(headers)
  //    }
  //  }

  import java.util.Dictionary

  private def dump(headers: Dictionary[_, _]) = {
    val result = scala.collection.mutable.ListBuffer[ManifestHeader]()
    val keys = headers.keys
    while ( {
      keys.hasMoreElements
    }) {
      val key = keys.nextElement.asInstanceOf[String]
      result += ManifestHeader(key, headers.get(key).toString)
    }
    //result.sorted((h1,h2) => h1.key.co)
    //result.stream.sorted((e1: Nothing, e2: Nothing) => e1.getKey.compareTo(e2.getKey)).collect(Collectors.toList)
    result.toList
  }

}

case class BundleDetails(
                          desc: BundleDescriptor,
                          location: String,
                          lastModified: Long,
                          startLevel: Long,
                          docUrl: String,
                          vendor: String,
                          copyright: String,
                          description: String,
                          bundleClasspath: String,
                          manifestHeaders: List[ManifestHeader]
                        )
