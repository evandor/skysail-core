package io.skysail.core.model

import scala.collection.JavaConverters._
import java.lang.reflect.Field
import io.skysail.core.ScalaReflectionUtils
import scala.reflect.runtime.universe._

case class EntityModel(entityClass: Type) {
  
  require(entityClass != null, "The provided entity class must not be null")
  
  def name() = entityClass.toString

  val fields = deriveFields()
//  val members = deriveMembers()
//  val decls = deriveDeclarations()
  
 // def fieldFor(id: String): Option[FieldModel] = fields.get(id) 

  private def deriveFields() = entityClass.members.collect { case m: MethodSymbol if m.isGetter => MemberModel(m) }.toList
  //private def deriveMembers() = entityClass.members.map(m => MemberModel(m)).toList
  //private def deriveDeclarations() = entityClass.decls.map(m => MemberModel(m)).toList

//  def toHtml() = s"""${this.getClass.getSimpleName}("$name")<br>
//        <br><u>Fields</u>: <ul>${printHtmlMap(fields)}</ul><br>"""
//  
//  override def toString() = s"""${this.getClass.getSimpleName}("$name")
//        Fields: ${printMap(fields)}"""
//  
  //private def filterFormFields(f: Field): Boolean = f.getAnnotation(classOf[io.skysail.core.html.Field]) != null

}