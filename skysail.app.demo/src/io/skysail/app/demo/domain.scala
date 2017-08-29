package io.skysail.app.demo

import org.osgi.service.cm.Configuration

case class Contact(name: String)

case class Doc(count: String, deleted: String)

case class EsIndex(health: String, status: String, index: String, pri: String, rep: String, `docs.count`: String)

case class DemoRoot(link: String, context: String, description: String)

case class Mapping()

case class ConfigDetails(c: Configuration) {

  import scala.collection.JavaConversions._

  def getProperties(): String = c.getProperties.keys().map {
    key =>
      if (key.contains("password")) {
        "<b>" + key + "</b>: ******"
      } else {
        "<b>" + key + "</b>: " + c.getProperties().get(key).toString()
      }
  }.mkString("<br>\n")
}