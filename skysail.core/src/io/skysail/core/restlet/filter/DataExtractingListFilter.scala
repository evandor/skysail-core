package io.skysail.core.restlet.filter

import org.slf4j.LoggerFactory
import io.skysail.restlet.AbstractListResourceFilter
import io.skysail.restlet.SkysailServerResource
import io.skysail.restlet.ListResponseWrapper
import io.skysail.core.domain.ScalaEntity
import io.skysail.restlet.Wrapper3

class ScalaDataExtractingListFilter[T <: List[_]] extends AbstractListResourceFilter[T] {

  override val log = LoggerFactory.getLogger(classOf[ScalaExceptionCatchingListFilter[T]])

  override def doHandle(resource: SkysailServerResource[_], responseWrapper: Wrapper3): FilterResult = {
    log.debug("entering {}#doHandle", this.getClass().getSimpleName());

    //val installation = ScalaCookiesUtils.getInstallationFromCookie(resource.getRequest()).orElse(null);
    val entity = resource.getEntity()
    if (entity.isInstanceOf[List[T]]) {
      val data = entity.asInstanceOf[List[T]]
      //sanitizeIds(data);

      responseWrapper.asInstanceOf[ListResponseWrapper[T]].setEntity(data);
//      resource.setCurrentEntity(data); // TODO why both wrapper AND
      // resource?
    } else {
      sanitizeIds(entity);

//      responseWrapper.setEntity(entity);
//      resource.setCurrentEntity(entity); // TODO why both wrapper AND
      // resource?

    }
    super.doHandle(resource, responseWrapper);
    return FilterResult.CONTINUE;
  }

  private def sanitizeIds(data: List[T]) = {
    data.foreach(element => {
      if (element.isInstanceOf[ScalaEntity[_]]) {
        replaceHash(element);
      }
    });
  }
  
  private def sanitizeIds( data: Any) = {
//		if (data.isInstanceOf[List[_]]) {
//			data.asInstanceOf[List[_]].foreach(element => {
//				if (element instanceof Entity) {
//					replaceHash(element);
//				}
//			});
//		} else if (data instanceof Entity) {
//			replaceHash(data);
//		}
	}

  private def replaceHash(element: Any) = {
    val identifiable = element.asInstanceOf[ScalaEntity[_]]
    if (identifiable.id != null) {
      try {
        val field = identifiable.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(identifiable, identifiable.id.get.toString().replace("#", ""));
      } catch {
        case e: Throwable => {
          val getIdMethod = identifiable.getClass().getDeclaredMethod("setId", classOf[String])
          getIdMethod.invoke(identifiable, identifiable.id.get.toString().replace("#", ""))
        }
      }

    }
  }

}