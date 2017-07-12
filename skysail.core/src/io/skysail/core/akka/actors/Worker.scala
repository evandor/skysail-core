package io.skysail.core.akka.actors

import io.skysail.core.akka.AbstractRequestHandlerActor
import akka.actor.Props

class Worker(val nextActorsProps: Props = null) extends AbstractRequestHandlerActor 