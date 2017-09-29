package io.skysail.app.elasticsearch


case class Doc(count: String, deleted: String)

case class EsIndex(health: String, status: String, index: String, pri: String, rep: String, `docs.count`: String)

case class Mapping()

