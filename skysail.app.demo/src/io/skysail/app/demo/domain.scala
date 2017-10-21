package io.skysail.app.demo

import org.osgi.service.cm.Configuration

final case class Contact(email: String, firstname: String, lastname: String) {
  require(!email.isEmpty, "email.empty")
}

case class Doc(count: String, deleted: String)

case class EsIndex(health: String, status: String, index: String, pri: String, rep: String, `docs.count`: String)

case class DemoRoot(link: String, context: String, description: String)

case class Mapping()

object ConfigDetails {
  def apply(c: Configuration): ConfigDetails = {
    import scala.collection.JavaConversions._
    val props: String = c.getProperties.keys().map {
      key =>
        if (key.contains("password")) {
          "<b>" + key + "</b>: ******"
        } else {
          "<b>" + key + "</b>: " + c.getProperties().get(key).toString()
        }
    }.mkString("<br>\n")
    ConfigDetails(props)
  }
}

case class ConfigDetails(properties: String)