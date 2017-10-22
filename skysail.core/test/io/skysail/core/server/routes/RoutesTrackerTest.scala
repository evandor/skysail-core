package io.skysail.core.server.routes

import akka.actor.ActorSystem
import io.skysail.core.app.ApplicationProvider
import org.mockito.Mockito
import org.scalatest.FunSuite

class RoutesTrackerTest extends FunSuite {

  private val system = Mockito.mock(classOf[ActorSystem])

  private val appInfoProvider = Mockito.mock(classOf[ApplicationProvider])

//  test("appInfo is added to tracker") {
//    val sys = ActorSystem("my")
//    val tracker = new RoutesTracker(sys)
//    var routes: scala.List[RouteMapping[_]] = List(RouteMapping("path", null))
//
//    Mockito.when(appInfoProvider.routes()).thenReturn(routes)
//    tracker.addRoutesFor(appInfoProvider)
//    assert(tracker.routes().size === 1)
//  }
}
