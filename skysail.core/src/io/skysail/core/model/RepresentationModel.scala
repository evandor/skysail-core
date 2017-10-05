package io.skysail.core.model

import java.util

import io.skysail.core.akka.ResponseEvent
import io.skysail.core.resources.Resource
import com.fasterxml.jackson.databind.ObjectMapper
import org.json4s.{DefaultFormats, Extraction, JArray, JObject, jackson}

class RepresentationModel(responseEvent: ResponseEvent[_]) {

  private val mapper = new ObjectMapper

  val rawData: List[Map[String, Any]] = deriveRawData()

  def deriveRawData(): List[Map[String, Any]] = {
    implicit val formats = DefaultFormats
    implicit val serialization = jackson.Serialization
    val r = responseEvent.resource
    val e = Extraction.decompose(r).asInstanceOf[JArray]

    /*if (r.isInstanceOf[List[_]]) {
      println("R: " + r)
      r.asInstanceOf[List[_]].map(row => {
        //val s = mapper.convertValue(row, classOf[util.LinkedHashMap[String, Any]])

        //      val written = write(e)

        println("row: " + s)
        s
      }).toList
    } else {
      Nil
    }*/

    e.children.map(c => {
      println("c: " + c)
      val c2 = c.asInstanceOf[JObject]
      val vals = c2.values

      vals
    })


  }


}
