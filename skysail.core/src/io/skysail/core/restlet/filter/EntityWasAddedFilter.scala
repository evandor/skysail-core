package io.skysail.core.restlet.filter

import io.skysail.restlet.AbstractResourceFilter
import io.skysail.restlet.SkysailServerResource
import io.skysail.restlet.Wrapper3
import org.slf4j.LoggerFactory

class EntityWasAddedFilter[T:Manifest](entity: T) extends AbstractResourceFilter[T] {

  override val log = LoggerFactory.getLogger(classOf[EntityWasAddedFilter[T]])

  override def doHandle(resource: SkysailServerResource[_], responseWrapper:  Wrapper3): FilterResult = {
    log.debug("entering {}#doHandle", this.getClass().getSimpleName());
    val infoMessage = resource.getClass().getSimpleName() + ".saved.success";
    //responseWrapper.addInfo(infoMessage);

//    if (application instanceof MessageQueueProvider) {
//      MessageQueueHandler messageQueueHandler = ((MessageQueueProvider) application)
//        .getMessageQueueHandler();
//      if (messageQueueHandler != null) {
//        Object currentEntity = resource.getCurrentEntity();
//        try {
//          String serialized = mapper.writeValueAsString(currentEntity);
//          messageQueueHandler.send("topic://entity." + currentEntity.getClass().getName().replace(".", "_") + ".post", serialized);
//        } catch (IOException e) {
//          log.error(e.getMessage(), e);
//        }
//      }
//    }
    super.doHandle(resource, responseWrapper);
    FilterResult.CONTINUE;
  }
}