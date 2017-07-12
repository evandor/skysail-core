package io.skysail.core.akka.actors

import io.skysail.core.akka.AbstractRequestHandlerActor
import akka.actor.Props

class Delegator(val nextActorsProps: Props = null) extends AbstractRequestHandlerActor