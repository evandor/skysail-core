package io.skysail.app.demo

import org.scalatest.FunSuite

import scala.collection.mutable.Stack
import spray.json._
import DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson._
import io.skysail.core.akka.Item
import spray.json.DefaultJsonProtocol

class DemoApplicationTest extends FunSuite {

  object MyJsonProtocol extends DefaultJsonProtocol {
    implicit val appFormat = jsonFormat1(Contact)
  }

  test("pop is invoked on a non-empty stack") {

    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    val oldSize = stack.size
    val result = stack.pop()
    assert(result === 2)
    assert(stack.size === oldSize - 1)
  }

  test("pop is invoked on an empty stack") {

    val emptyStack = new Stack[Int]
    intercept[NoSuchElementException] {
      emptyStack.pop()
    }
    assert(emptyStack.isEmpty)
  }

  test("a") {
    import MyJsonProtocol._
    //implicit val itemFormat = jsonFormat1(Application)
    val app1 = new Contact("hier")
    val app2 = new Contact("dort")
    val l = List(app1, app2)
    val str = l.toJson.toString()
    println(str)
  }
}
