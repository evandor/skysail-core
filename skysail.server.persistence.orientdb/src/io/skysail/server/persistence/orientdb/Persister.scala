package io.skysail.server.persistence.orientdb

import com.tinkerpop.blueprints.impls.orient.OrientGraph
import com.tinkerpop.blueprints.impls.orient.OrientVertex
import com.fasterxml.jackson.databind.ObjectMapper
import scala.collection.JavaConverters._
import com.tinkerpop.blueprints.Vertex
import org.slf4j.LoggerFactory

import org.json4s._
import org.json4s.jackson.JsonMethods._

object Persister {
  def getMethodName(prefix: String, key: String): String = {
    return new StringBuilder(prefix).append(key.substring(0, 1).toUpperCase()).append(key.substring(1)).toString()
  }
}

class Persister(db: OrientGraph) {

  private val log = LoggerFactory.getLogger(this.getClass)

  val edgeHandler = new EdgeHandler(/*(identifiable) -> execute(identifiable), */ db)

  val mapper = new ObjectMapper()

  def persist(entity: Any): String = {
    runInTransaction(entity).toString
  }

  private def runInTransaction(entity: Any): OrientVertex = {
    try {
      val result = execute(entity);
      db.commit();
      return result;
    } catch {
      case e: Exception =>
        db.rollback();
        throw new RuntimeException("Database Problem, rolled back transaction", e);
    } finally {
      db.shutdown();
    }
  }

  def execute(entity: Any): OrientVertex = {
    val vertex = determineVertex(entity);
    try {
      //val removeRelationData = AnnotationUtils.removeRelationData(entity);
      println("ENTITY: " + entity)

      println(parse(""" { "numbers" : [1, 2, 3, 4] } """))

      implicit val formats = DefaultFormats
      val e = Extraction.decompose(entity).asInstanceOf[JObject]
      println("AST: " + e)

      //val props = mapper.convertValue(entity, classOf[java.util.Map[String, Any]]).asScala.toMap
      //println("PROPS: " + props)
      //props.keys.foreach(setPropertyOrCreateEdge(entity, vertex, props));

      e.obj.foreach(jField => setPropertyOrCreateEdge2(entity, vertex, jField))

      vertex;
    } catch {
      case e: Exception =>
        //log.error(e.getMessage(), e);
        throw new RuntimeException("Problem when persisting entity", e);
    }
  }

  private def determineVertex(entity: Any): OrientVertex = {
    var vertex: OrientVertex = null
    //        if (entity.getId() != null) {
    //            vertex = db.getVertex(entity.getId());
    //        } else {
    vertex = db.addVertex("class:" + entity.getClass().getName().replace(".", "_"), Nil: _*);
    //        }
    vertex;
  }

  def setPropertyOrCreateEdge(entity: Any, vertex: OrientVertex, props: Map[String, Any]): String => Unit = {
    key: String => {
      if (isProperty(entity, key)) {
        //System.out.println(entity.getClass() + ": " + key + ":= \""+properties.get(key)+"\"");
        if (props.get(key) != null && !("class".equals(key))) {
          setProperty(entity, vertex, key);
        }
      } else {
        System.out.println(entity.getClass() + ": " + key + ":= [EDGE]");
        try {
          //            edgeHandler.handleEdges(entity, vertex, properties, key);
        } catch {
          case e: Exception => log.error(e.getMessage(), e);
        }
      }
    }
  }

  def setPropertyOrCreateEdge2(entity: Any, vertex: OrientVertex, jValue: JField) = {
    val key = jValue._1
    if (isProperty(entity, key)) {
      //if (props.get(key) != null && !("class".equals(key))) {
      setProperty2(vertex, jValue);
      //}
    } else {
      try {
        //            edgeHandler.handleEdges(entity, vertex, properties, key);
      } catch {
        case e: Exception => log.error(e.getMessage(), e);
      }
    }
  }

  private def isProperty(entity: Any, key: String): Boolean = {
    //        if (applicationModel == null) {
    //            return !edges.contains(key);
    //        }
    //        EntityModel entityModel = applicationModel.getEntity(entity.getClass().getName());
    //        if (entityModel == null) {
    //            return true;
    //        }
    //        List<EntityRelation> relations = entityModel.getRelations();
    //        boolean relationExists = relations.stream()
    //                .map(EntityRelation::getName)
    //                .filter(n -> n.equals(key))
    //                .findFirst().isPresent();
    //
    //        if (relationExists) {
    //        	return false;
    //        }
    //        if (entityModel instanceof SkysailEntityModel) {
    //            SkysailEntityModel sem = (SkysailEntityModel)entityModel;
    //            SkysailFieldModel field = (SkysailFieldModel) sem.getField(entity.getClass().getName() + "|" + key);
    //            if (field == null) {
    //            	log.warn("could not determine field for id '{}'", entity.getClass().getName() + "|" + key);
    //            	return true;
    //            }
    //            if (field.getEntityType() != null) {
    //                return false;
    //            }
    //        }
    return true;
  }

  private def setProperty(entity: Any, vertex: Vertex, key: String): Unit = {
    try {
      //      if (isOfBooleanType(entity, key)) {
      //        setVertexProperty("is", entity, vertex, key);
      //      } else {
      setVertexProperty("get", entity, vertex, key);
      //      }
    } catch {
      case e: Exception => log.error(e.getMessage(), e);
    }
  }

  def setProperty2(vertex: OrientVertex, jValue: JField) = {
    jValue._2 match {
      case string: JString =>
        log info s"setting Property('${jValue._1}','${string.s}')"; vertex.setProperty(jValue._1, string.s)
      case _: Any => log warn "no idea what to do"
    }
  }

  private def setVertexProperty(prefix: String, entity: Any, vertex: Vertex, key: String) = {
    val method = entity.getClass().getMethod(Persister.getMethodName(prefix, key));
    val result = method.invoke(entity);

    //    if (result instanceof ValueObject) {
    //      vertex.setProperty(key, ((ValueObject)result).getValue().toString());
    //    } else {
    vertex.setProperty(key, result);
    //    }
  }

}