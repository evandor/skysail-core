package io.skysail.core.app

trait ApplicationProvider extends Ordered[ApplicationProvider] {
  def getSkysailApplication(): SkysailApplication
  def compare(that: ApplicationProvider): Int = getSkysailApplication().getName.compareTo(that.getSkysailApplication().getName)
}