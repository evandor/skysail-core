package io.skysail.core.restlet

import scala.annotation.meta._
import org.junit.Test
import org.junit.Assert._
import org.mockito.Mockito._
import org.hamcrest.CoreMatchers._
import org.restlet.data.Form
import org.junit.Ignore
import javax.validation.constraints._
import io.skysail.core.restlet.resources._
import io.skysail.core.restlet.resources.PostEntityServerResource

@Ignore
class PostEntityServerResourceTest {

  case class TestNote(@(NotNull @field) val content: String)
  
  class PostTestNoteServerResource extends PostEntityServerResource[TestNote] {
    def createEntityTemplate(): TestNote = new TestNote("hi")

    def addEntity(entity: TestNote): TestNote = {
      null
    }

  }

  @Test
  def atest(): TestNote = {
    val postResource = new PostTestNoteServerResource()
    val form = new Form()
    //postResource.post(form, Variant.A)
    
    
//    val entityModel = mock(classOf[SkysailEntityModel])
//    val fields = ReflectionUtils.getInheritedFields(classOf[TestNote])
//    val ssfm = new ScalaSkysailFieldModel(entityModel, fields.get(0))
//    val sff = new ScalaFormField(ssfm, TestNote("content"), null)
//    assertThat(sff.isMandatory(), is(true))
    null
  }

}