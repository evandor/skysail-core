package io.skysail.core.restlet.filter

import org.restlet.Context
import org.restlet.Response
import org.restlet.Request
import org.restlet.routing.Filter

class OriginalRequestFilter(context: Context) extends Filter(context) {

  override def beforeHandle(request: Request, response: Response): Int = FilterResult.CONTINUE.ordinal()

}