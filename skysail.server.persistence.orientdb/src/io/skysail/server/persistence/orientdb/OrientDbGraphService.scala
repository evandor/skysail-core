package io.skysail.server.persistence.orientdb

import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import org.slf4j.LoggerFactory
import com.orientechnologies.orient.graph.sql.OGraphCommandExecutorSQLFactory
import com.orientechnologies.orient.graph.sql.functions.OGraphFunctionFactory
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx
import com.orientechnologies.orient.core.sql.OSQLEngine
import com.orientechnologies.orient.core.sql.functions.OSQLFunction
import scala.collection.JavaConverters._
import com.orientechnologies.orient.`object`.db.OObjectDatabaseTx
import com.orientechnologies.orient.core.db.OPartitionedDatabasePool
import io.skysail.api.persistence.DbService
import com.tinkerpop.blueprints.impls.orient.OrientVertex
import com.tinkerpop.blueprints.impls.orient.OrientGraph
import com.orientechnologies.orient.core.command.OCommandRequest
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.orientechnologies.orient.core.record.impl.ODocument
import org.json4s._
import org.json4s.jackson.JsonMethods._
import scala.reflect.ClassTag
import com.tinkerpop.blueprints.impls.orient.OrientDynaElementIterable

class OrientDbGraphService(url: String, user: String, pass: String) extends DbService {

  private var log = LoggerFactory.getLogger(this.getClass)

  var graphDbFactory: OrientGraphFactory = null

  log info s"activating ${this.getClass().getName()}"

  //http://stackoverflow.com/questions/30291359/orient-db-unable-to-open-any-kind-of-graph
  if (url.startsWith("memory:")) {
    // https://github.com/orientechnologies/orientdb/issues/5105
    // com.orientechnologies.common.util.OClassLoaderHelper
    new OGraphCommandExecutorSQLFactory()
    new OrientGraphNoTx(url)
  }

  graphDbFactory = new OrientGraphFactory(url, user, pass).setupPool(1, 10)

  val graphFunctions = new OGraphFunctionFactory()
  val names = graphFunctions.getFunctionNames().asScala

  for (name <- names) {
    OSQLEngine.getInstance().registerFunction(name, graphFunctions.createFunction(name))
    val function = OSQLEngine.getInstance().getFunction(name)
    if (function != null) {
      log debug s"ODB graph function [${name}] is registered: [${function.getSyntax()}]"
    } else {
      log warn "ODB graph function [{$name}] NOT registered!!!"
    }
  }

  def createWithSuperClass(superClass: String, vertices: String*) = {
    val objectDb = getObjectDb()
    vertices.foreach(v => {
      if (objectDb.getMetadata().getSchema().getClass(v) == null) {
        val vertexClass = objectDb.getMetadata().getSchema().getClass(superClass)
        objectDb.getMetadata().getSchema().createClass(v).setSuperClass(vertexClass)
      }
    })
  }

  private def getObjectDb(): OObjectDatabaseTx = {
    val opDatabasePool = new OPartitionedDatabasePool(url, user, pass)
    val oDatabaseDocumentTx = opDatabasePool.acquire()
    new OObjectDatabaseTx(oDatabaseDocumentTx)
  }

  def register(classes: Class[_]*) = {
    val db = getObjectDb()
    try {
      classes.foreach(cls => {
        log.debug("registering class '{}' @ orientDB", cls)
        db.getEntityManager().registerEntityClass(cls)
      })
    } finally {
      db.close()
    }
  }

  def persist(entity: Any): String = {
    new Persister(getGraphDb() /*, applicationModel*/ ).persist(entity)
  }

  def findGraphs[T: Manifest](cls: Class[T], sql: String /*, Map<String, Object> params*/ ): List[T] = {
    val graph = getGraphDb()
    val oCommand = new OCommandSQL(sql)
    val execute = graph.command(oCommand)
    val results: OrientDynaElementIterable = execute.execute()
    val result = scala.collection.mutable.ListBuffer[T]()

    results.asScala.foreach(v => {
      val r = v.asInstanceOf[OrientVertex]
      result += documentToBean(r.getRecord(), cls)
    })
    
    result.toList
  }

  private def getGraphDb(): OrientGraph = {
    val graph = graphDbFactory.getTx()
    // https://github.com/orientechnologies/orientdb/issues/2722
    graph.setStandardElementConstraints(false)
    graph
  }

  def documentToBean[T: Manifest](doc: ODocument, cls: Class[T]): T = {
    //populateProperties(document.toMap(), bean, new SkysailBeanUtils(bean, Locale.getDefault(), appService));
    //populateOutgoingEdges(document, bean);
    //populateIngoingEdge(document, bean);
    //doc.fieldNames().foreach(fieldName => )
    val ast = parse(doc.toJSON())
    implicit val formats = DefaultFormats
    println("AST" + ast)
    ast.extract[T]
  }

}