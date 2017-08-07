package io.skysail.core.akka.actors

import io.skysail.core.akka.AbstractRequestHandlerActor
import akka.actor.Props
import io.skysail.core.akka._
import akka.http.scaladsl.model.{ StatusCodes, Uri }

class Redirector(val nextActorsProps: Props) extends AbstractRequestHandlerActor {

  override def doResponse(res: ResponseEvent) = {
    //res.req.ctx.redirect("http://www.heise.de", StatusCodes.SeeOther)
    //val d = res.req.request.
    //redirect(Uri("http://example.com"), TemporaryRedirect)
  }
}