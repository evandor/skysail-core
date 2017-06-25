package io.skysail.core.app

import akka.http.scaladsl.server.Route

trait ApplicationRoutesProvider {
  def routes(): List[Route]
}