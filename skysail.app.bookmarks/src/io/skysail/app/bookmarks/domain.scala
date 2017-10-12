package io.skysail.app.bookmarks

import io.skysail.api.ddd.Entity

case class Bookmark(id: Option[String], title: String, url: String) extends Entity[String]

