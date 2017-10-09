package io.skysail.app.bookmarks

import io.skysail.api.persistence.DbService

class BookmarksRepository(dbService: DbService) {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[Bookmark]))
  dbService.register(classOf[Bookmark])

  def save(entity: Any): String = {
    dbService.persist(entity)
  }

  def find( /*Filter filter, Pagination pagination*/ ) = {
    val sql = "SELECT * from " + DbService.tableNameFor(classOf[Bookmark])
    //                + (!StringUtils.isNullOrEmpty(filter.getPreparedStatement()) ? " WHERE " + filter.getPreparedStatement()
    //                        : "")
    //                + " " + limitClause(pagination);
    //pagination.setEntityCount(count(filter));
    println("executing sql " + sql)
    dbService.findGraphs(classOf[Bookmark], sql) //, filter.getParams());
  }

}