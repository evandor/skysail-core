package io.skysail.app.demo

case class Contact(name: String)

case class Doc(count:String, deleted:String)

case class EsIndex(health: String, status: String, index: String, pri: String, rep: String, `docs.count`: String)

case class DemoRoot(link: String, context: String, description: String)

case class Mapping()