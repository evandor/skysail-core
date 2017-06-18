package io.skysail.core.restlet.utils

import io.skysail.core.forms.ScalaFormField

object ScalaMessagesUtils {
  def getBaseKey(entityClass: Class[_], f: ScalaFormField) = f.getId()
  def getSimpleName(f: ScalaFormField) = f.getId()
}