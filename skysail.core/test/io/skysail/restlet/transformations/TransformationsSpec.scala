package io.skysail.restlet.transformations

import collection.mutable.Stack
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import org.slf4j.LoggerFactory
import org.restlet.data.Form
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{ read, write }
import io.skysail.core.restlet.transformations.Transformations

class TransformationsSpec extends FlatSpec {

  private implicit val formats = DefaultFormats

//  "A SimpleEntity" should "be parsed from a form representation" in {
//    val form = new Form()
//    form.add("io.skysail.restlet.transformations.SimpleEntity|title", "a title")
//    val res = Transformations.jsonFrom[SimpleEntity](form)
//    println(pretty(render(res)))
//    val outer = res.extract[OuterEntity]
//    println(outer)
//  }

  "A nested entity" should "be parsed from a form representation" in {
    val form = new Form()
    form.add("io.skysail.restlet.transformations.Pact|title", "a title")
    val res = Transformations.jsonFrom[Pact](form)
    println(pretty(render(res)))
    val outer = res.extract[Pact]
    println(outer)
  }

  // JObject(List((title,JString(pactTitle_3o03bbsvd14pl9co4vhs0a6v9t))))

//  "A" should "b" in {
//    val jstring = JString("pactTitle")
//    val jList = JArray(List(jstring))
//    val jobject = new JObject(List(JField("title", jstring)))
//    println(jobject)
//    val res = jobject.extract[SimpleEntity]
//    println(res)
//  }
  
//  "A" should "b2" in {
//    val jstring = JString("pactTitle")
//    val jList = JArray(List(jstring))
//    val jobject = new JObject(List(JField("title", jstring)))
//    println(jobject)
//    val res = jobject.extract[Pact]
//    println(res)
//  }
}