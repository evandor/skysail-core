package io.skysail.core.app.menus

case class MenuItem(label: String, icon: String, routerLink: Option[String] = None, items: Option[List[MenuItem]] = None)