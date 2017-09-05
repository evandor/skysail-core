package io.skysail.core.app.menus

case class MenuItem(label: String, icon: String, url: Option[String] = None, items: Option[List[MenuItem]] = None)