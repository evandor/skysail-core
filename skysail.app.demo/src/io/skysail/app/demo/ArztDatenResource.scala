package io.skysail.app.demo

import io.skysail.core.akka.RequestEvent
import io.skysail.core.resources.{AsyncEntityResource, AsyncListResource}


class AerzteDatenResource extends AsyncListResource[Arztdaten] {

//  override def get(requestEvent: RequestEvent) {
//    requestEvent.controllerActor ! Arztdaten("titel", "vorname")
//  }
  override def get(requestEvent: RequestEvent) = {
      requestEvent.controllerActor ! List(Arztdaten("titel", "vorname"), Arztdaten("Dr.","Simon"))
  }
}
