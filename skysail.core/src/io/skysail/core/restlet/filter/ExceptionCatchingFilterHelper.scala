package io.skysail.core.restlet.filter

import io.skysail.core.app.SkysailApplication
import io.skysail.restlet.Wrapper3
import org.slf4j.LoggerFactory
import org.restlet.data.Status

object ExceptionCatchingFilterHelper {

  val log = LoggerFactory.getLogger(this.getClass)

  def handleError(e: Exception, application: SkysailApplication, responseWrapper: Wrapper3, cls: Class[_]): Unit = {
    log.error(e.getMessage(), e);
    val genericErrorMessageForGui = cls.getSimpleName() + ".saved.failure"
    //responseWrapper.addError(genericErrorMessageForGui)
    val response = responseWrapper.getResponse()
    response.setStatus(Status.SERVER_ERROR_INTERNAL)
    //responseWrapper.addInfo(e.getMessage())
  }

}