package io.skysail.api.persistence

import org.osgi.annotation.versioning.ProviderType

object DbService {
  def tableNameFor(cls: Class[_]): String = cls.getName().replace(".", "_")
}

@ProviderType
trait DbService {

  def createWithSuperClass(superClass: String, vertices: String*)

  def register(classes: Class[_]*)

  def persist(entity: Any): String
  
  def findGraphs[T:Manifest](cls: Class[T], sql: String /*, Map<String, Object> params*/ ): List[T]
}