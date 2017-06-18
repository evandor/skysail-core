package io.skysail.core.restlet.menu

trait Category
case class APPLICATION_MAIN_MENU() extends Category

class MenuItem(val name: String, val link: String) {//extends ScalaEntity[String] {

  var category:Category = null
  
  def setCategory(cat: Category) = this.category = category
}