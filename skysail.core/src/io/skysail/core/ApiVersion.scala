package io.skysail.core

case class ApiVersion(versionNr: Int) {
  require(versionNr > 0, "version number must be greater zero")
  @Deprecated def getVersionPath() = new StringBuilder("/v").append(versionNr).toString()
  override def toString() = "v" + versionNr
}