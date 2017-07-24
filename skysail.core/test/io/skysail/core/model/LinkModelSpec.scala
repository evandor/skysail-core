//package io.skysail.core.model
//
//import collection.mutable.Stack
//import org.scalatest._
//import org.junit.runner.RunWith
//import org.scalatest.junit.JUnitRunner
//import org.slf4j.LoggerFactory
//
//@RunWith(classOf[JUnitRunner])
//class LinkModelSpec extends FlatSpec {
//
//  "A LinkModel" should "be able to parse a linkheader" in {
//    val lM = LinkModel.fromLinkheader("""</demo/v1>; rel="canonical"; title="list Todos"; verbs="GET"""")
//    assert(lM.getTitle() == "list Todos")
//    assert(lM.getUri() == "/demo/v1")
//  }
//}