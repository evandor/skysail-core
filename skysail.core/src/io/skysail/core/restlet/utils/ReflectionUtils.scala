//package io.skysail.core.restlet.utils
//
//import scala.collection.mutable.ListBuffer
//import java.lang.reflect.ParameterizedType
//import java.lang.reflect.Type
//import java.lang.reflect.Field
//
//object ScalaReflectionUtils {
//
//  val inheritedFieldsCache = scala.collection.mutable.Map[Class[_], List[java.lang.reflect.Field]]()
//
//  def getInheritedFields(theType: Class[_]): List[java.lang.reflect.Field] = {
//
//    if (inheritedFieldsCache.contains(theType)) {
//      return inheritedFieldsCache.get(theType).get
//    }
//    var result = ListBuffer[java.lang.reflect.Field]()
//
//    var i = theType
//    while (i != null && i != classOf[Object]) {
//      while (i != null && i != classOf[Object]) {
//        for (field <- i.getDeclaredFields()) {
//          if (!field.isSynthetic()) {
//            result += field
//          }
//        }
//        i = i.getSuperclass()
//      }
//    }
//    inheritedFieldsCache += theType -> result.toList
//    result.toList
//  }
//
//  def getParameterizedType(cls: Class[_]): Class[_] = {
//    val parameterizedType = getParameterizedType1(cls)
//    if (parameterizedType == null) {
//      return classOf[Any]
//    }
//    val typeArgumentsSize = parameterizedType.getActualTypeArguments().size
//    val firstActualTypeArgument = parameterizedType.getActualTypeArguments()(0)
//    if (firstActualTypeArgument.getTypeName().startsWith("java.util.Map")) {
//      return classOf[Map[_, _]]
//    }
//    try {
//      val f = firstActualTypeArgument.getClass.getDeclaredField("actualTypeArguments")
//      //firstActualTypeArgument.
//      f.setAccessible(true)
//      val t = f.get(firstActualTypeArgument).asInstanceOf[Array[Type]]
//      return t(0).asInstanceOf[Class[_]]
//    } catch {
//      case e:Any =>
//    }
//    return firstActualTypeArgument.asInstanceOf[Class[_]]
//  }
//
//  private def getParameterizedType1(cls: Class[_]): ParameterizedType = {
//    val genericSuperclass = cls.getGenericSuperclass()
//    if (genericSuperclass == null) {
//      val genericInterfaces = cls.getGenericInterfaces()
//      val pt = genericInterfaces.filter(i => i.isInstanceOf[ParameterizedType]).headOption
//      if (pt.isDefined) {
//        return pt.get.asInstanceOf[ParameterizedType]
//      }
//      return null
//    }
//    if (genericSuperclass.isInstanceOf[ParameterizedType]) {
//      return genericSuperclass.asInstanceOf[ParameterizedType]
//    }
//    return getParameterizedType1(cls.getSuperclass())
//  }
//
//  def getParameterizedType(field: Field): Type = {
//    val theType = field.getGenericType()
//    if (theType.isInstanceOf[ParameterizedType]) {
//      val pType = theType.asInstanceOf[ParameterizedType]
//      return pType.getActualTypeArguments()(0)
//    }
//    return field.getType()
//  }
//
//}