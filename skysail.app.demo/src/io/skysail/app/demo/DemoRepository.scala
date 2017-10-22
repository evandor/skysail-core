package io.skysail.app.demo

import io.skysail.api.persistence.DbService

class DemoRepository(dbService: DbService) {
  dbService.createWithSuperClass("V", classOf[Contact].getSimpleName)
  dbService.register(classOf[Contact])

  def save(entity: Any): String = {
    dbService.persist(entity)
  }

  def find( /*Filter filter, Pagination pagination*/ ) = {
    val sql = "SELECT * from " + DbService.tableNameFor(classOf[Contact])
    //                + (!StringUtils.isNullOrEmpty(filter.getPreparedStatement()) ? " WHERE " + filter.getPreparedStatement()
    //                        : "")
    //                + " " + limitClause(pagination);
    //pagination.setEntityCount(count(filter));
    //println("executing sql " + sql)
    dbService.findGraphs(classOf[Contact], sql) //, filter.getParams());
  }

}