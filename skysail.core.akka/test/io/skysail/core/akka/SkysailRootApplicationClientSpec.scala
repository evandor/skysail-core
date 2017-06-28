package io.skysail.core.akka

import org.json4s.DefaultFormats
import org.json4s.native.Serialization._
import org.scalatest.{ FunSpec, Matchers }

class SkysailRootApplicationClientSpec extends FunSpec with Matchers {

  import com.itv.scalapact.ScalaPactForger._

  implicit val formats = DefaultFormats

  describe("Connecting to the Provider service") {

    it("should be able to fetch results") {

      val people = List("Bob", "Fred", "Harry")

      val body = write(
        Results(
          count = 3,
          results = people))

      forgePact
        .between("Consumer")
        .and("Provider")
        .addInteraction(
          interaction
            .description("Fetching results")
            .given("Results: Bob, Fred, Harry")
            .uponReceiving("/results")
            .willRespondWith(200, body))
        .runConsumerTest { mockConfig =>

          val results = SkysailRootApplicationClient.fetchResults(mockConfig.baseUrl)

          results.isDefined shouldEqual true
          results.get.count shouldEqual 3
          results.get.results.forall(p => people.contains(p)) shouldEqual true

        }
    }
  }
}