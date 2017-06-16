package io.skysail.core.restlet

import org.slf4j.LoggerFactory
import io.skysail.core.restlet.filter.FilterResult
import io.skysail.core.restlet.filter.FilterResult._

trait ResourceFilter[T] {

  val log = LoggerFactory.getLogger(this.getClass())

  var next: ResourceFilter[T] = null

  protected def getLast() = {
    var result = this;
    while (result.getNext() != null) {
      result = result.getNext();
    }
    result;
  }

  protected def beforeHandle(resource: SkysailServerResource[_], responseWrapper: Wrapper3) = CONTINUE

  protected final def handleMe(resource: SkysailServerResource[_], responseWrapper: Wrapper3): Unit = {
    beforeHandle(resource, responseWrapper) match {
      case CONTINUE => {
        doHandle(resource, responseWrapper) match {
          case CONTINUE => afterHandle(resource, responseWrapper)
          case SKIP => log.info("skipping filter chain at filter {}", this.getClass().getSimpleName());
          case STOP => log.info("stopping filter chain at filter {}", this.getClass().getSimpleName());
        }
      }
      case SKIP => {
        log.info("skipping filter chain at filter {}", this.getClass().getName());
        afterHandle(resource, responseWrapper);
      }
      case STOP => log.info("stopping filter chain at filter {}", this.getClass().getName());
    }
  }

  protected def doHandle(resource: SkysailServerResource[_], responseWrapper: Wrapper3): FilterResult = {
    val next = getNext();
    if (next != null) {
      // logger.debug("next filter in chain: {}", next.getClass().getSimpleName());
      next.handleMe(resource, responseWrapper);
    }
    CONTINUE;
  }
  
  def calling(next: ResourceFilter[T]) = {
    val lastInChain = getLast();
    lastInChain.setNext(next);
    this;
  }


  def afterHandle(resource: SkysailServerResource[_], responseWrapper: Wrapper3): Unit = {}

  protected def getNext(): ResourceFilter[T] = next
  private def setNext(next: ResourceFilter[T]) = this.next = next
}