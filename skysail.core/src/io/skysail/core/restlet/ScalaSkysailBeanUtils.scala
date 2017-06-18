package io.skysail.core.restlet

import java.util.Locale
import io.skysail.core.restlet.utils.ScalaSkysailBeanUtilsBean
import scala.collection.JavaConverters._
import io.skysail.core.app.SkysailApplicationService

case class ScalaSkysailBeanUtils[T](bean: T, locale: Locale, service: SkysailApplicationService) {
  
  val beanUtilsBean = new ScalaSkysailBeanUtilsBean(bean, locale)

  def populate(bean: Any, properties: Map[String, Any]) = {
    beanUtilsBean.populate(bean, properties.asJava);
  }

  def copyProperties(dest: T, orig: T, resource: SkysailServerResource[_]): Unit = {
    //        Map<String, FormField> formfields = FormfieldUtils.determineFormfields(resource, service);
    //        PropertyDescriptor[] origDescriptors = beanUtilsBean.getPropertyUtils().getPropertyDescriptors(orig);
    //        Class<?> parameterizedType = resource.getParameterizedType();
    //        for (int i = 0; i < origDescriptors.length; i++) {
    //            String name = origDescriptors[i].getName();
    //            String normalizedName = parameterizedType.getName() + "|" + name;
    //            if ("class".equals(name) || ignore(formfields, normalizedName)) {
    //                continue;
    //            }
    //            if (beanUtilsBean.getPropertyUtils().isReadable(orig, name) &&
    //                    beanUtilsBean.getPropertyUtils().isWriteable(dest, name)) {
    //                try {
    //                    Object value =
    //                            beanUtilsBean.getPropertyUtils().getSimpleProperty(orig, name);
    //                    beanUtilsBean.copyProperty(dest, name, value);
    //                } catch (NoSuchMethodException e) {
    //                    // Should not happen
    //                }
    //            }
    //        }

  }

}