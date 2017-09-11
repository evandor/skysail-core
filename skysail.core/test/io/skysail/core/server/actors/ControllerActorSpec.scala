package io.skysail.core.akka

import akka.actor.ActorSystem
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.ContentTypes.`application/json`
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.Unmarshaller.UnsupportedContentTypeException
import akka.http.scaladsl.unmarshalling.{ Unmarshal, Unmarshaller }
import akka.stream.ActorMaterializer
import org.json4s.{ jackson, native, DefaultFormats }
import org.scalatest.{ AsyncWordSpec, BeforeAndAfterAll, Matchers }
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

object Json4sSupportSpec {

  case class Foo(bar: String) {
    require(bar == "bar", "bar must be 'bar'!")
  }
}

@RunWith(classOf[JUnitRunner])
final class ControllerActorSpec extends AsyncWordSpec with Matchers with BeforeAndAfterAll {
  import Json4sSupport._
  import Json4sSupportSpec._

  private implicit val system  = ActorSystem()
  private implicit val mat     = ActorMaterializer()
  private implicit val formats = DefaultFormats

  private val foo = Foo("bar")

  "Json4sSupport" should {
    "enable marshalling and unmarshalling objects for `DefaultFormats` and `jackson.Serialization`" in {
      implicit val serialization = jackson.Serialization
      Marshal(foo)
        .to[RequestEntity]
        .flatMap(Unmarshal(_).to[Foo])
        .map(_ shouldBe foo)
    }

    "enable marshalling and unmarshalling objects for `DefaultFormats` and `native.Serialization`" in {
      implicit val serialization = native.Serialization
      Marshal(foo)
        .to[RequestEntity]
        .flatMap(Unmarshal(_).to[Foo])
        .map(_ shouldBe foo)
    }

    "provide proper error messages for requirement errors" in {
      implicit val serialization = native.Serialization
      val entity =
        HttpEntity(MediaTypes.`application/json`, """{ "bar": "baz" }""")
      Unmarshal(entity)
        .to[Foo]
        .failed
        .map(_ should have message "requirement failed: bar must be 'bar'!")
    }

    "fail with NoContentException when unmarshalling empty entities" in {
      implicit val serialization = native.Serialization
      val entity                 = HttpEntity.empty(`application/json`)
      Unmarshal(entity)
        .to[Foo]
        .failed
        .map(_ shouldBe Unmarshaller.NoContentException)
    }

    "fail with UnsupportedContentTypeException when Content-Type is not `application/json`" in {
      implicit val serialization = native.Serialization
      val entity                 = HttpEntity("""{ "bar": "bar" }""")
      Unmarshal(entity)
        .to[Foo]
        .failed
        .map(_ shouldBe UnsupportedContentTypeException(`application/json`))
    }

    "allow unmarshalling with passed in Content-Types" in {
      implicit val serialization = native.Serialization
      val foo                    = Foo("bar")
      val `application/json-home` =
        MediaType.applicationWithFixedCharset("json-home", HttpCharsets.`UTF-8`, "json-home")

      final object CustomJson4sSupport extends Json4sSupport {
        override def unmarshallerContentTypes = List(`application/json`, `application/json-home`)
      }
      import CustomJson4sSupport._

      val entity = HttpEntity(`application/json-home`, """{ "bar": "bar" }""")
      Unmarshal(entity).to[Foo].map(_ shouldBe foo)
    }
  }

  override protected def afterAll() = {
    Await.ready(system.terminate(), 42.seconds)
    super.afterAll()
  }
}
