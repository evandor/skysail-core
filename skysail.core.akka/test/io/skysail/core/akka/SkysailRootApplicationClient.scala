package io.skysail.core.akka

import scalaj.http.{Http, HttpResponse}

import org.json4s.DefaultFormats
import org.json4s.native.JsonParser._

object SkysailRootApplicationClient {
  
  private implicit val formats = DefaultFormats

  def fetchResults(baseUrl: String): Option[Results] = {
    Http(baseUrl + "/results").asString match {
      case r: HttpResponse[String] if r.is2xx =>
        parse(r.body).extractOpt[Results]

      case _ =>
        None
    }
  }

}

case class Results(count: Int, results: List[String])

case class Token(token: String)