package io.skysail.app.bookmarks

import io.skysail.api.persistence.DbService

class BookmarksRepository(dbService: DbService) {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[Bookmark]))
  dbService.register(classOf[Bookmark])

  def save(entity: Any): String = {
    dbService.persist(entity)
  }

  def find( /*Filter filter, Pagination pagination*/ ): List[Bookmark] = {
    val sql = "SELECT * from " + DbService.tableNameFor(classOf[Bookmark])
    //                + (!StringUtils.isNullOrEmpty(filter.getPreparedStatement()) ? " WHERE " + filter.getPreparedStatement()
    //                        : "")
    //                + " " + limitClause(pagination);
    //pagination.setEntityCount(count(filter));
    println("executing sql " + sql)
    dbService.findGraphs(classOf[Bookmark], sql) //, filter.getParams());
  }

  def find(id: String): Option[Bookmark] = {
    val sql = s"SELECT * from ${DbService.tableNameFor(classOf[Bookmark])} where id='${id}'"
    println("executing sql " + sql)
    val res = dbService.findGraphs(classOf[Bookmark], sql) //, filter.getParams());
    if (res.size == 0) None else res.headOption
  }
}