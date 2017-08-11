//package io.skysail.core.akka
//
//import akka.http.scaladsl.server.Directives
//import akka.http.scaladsl.marshallers.sprayjson._
//import spray.json.DefaultJsonProtocol
//import akka.util.Timeout
//import scala.concurrent.duration.DurationInt
//import akka.actor.ActorSystem
//import io.skysail.core.server.ApplicationsActor
//import akka.actor.ActorSelection
//import akka.pattern.ask
//
//final case class Item(name: String, id: Long)
//final case class Order(items: List[Item])
//
//trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
//  implicit val itemFormat = jsonFormat2(Item)
//  implicit val orderFormat = jsonFormat1(Order) // contains List[Item]
//}
//
//object MyJsonService {
//  def getApplicationActorSelection(system: ActorSystem, name: String) = {
//    val applicationActorPath = "/user/" + classOf[ApplicationsActor].getSimpleName + "/" + name
//    system.actorSelection(applicationActorPath)
//  }
//}
//
//class MyJsonService extends Directives with JsonSupport {
//
//  def route(appActorSelection: ActorSelection, cls: Class[_ <: ResourceActor[_]]) =
//    pathSingleSlash {
//      get {
//        extractRequestContext {
//          ctx => {
//            complete(Item("thing2", 42))
//          }
//        }  
//      }
//    } ~
//      path("home") {
//        get {
//          extractRequestContext {
//            ctx =>
//              {
//                //complete(Item("thing3", 43))   
//                //log info s"executing route#${counter.incrementAndGet()}"
//                implicit val askTimeout: Timeout = 3.seconds
//                //println(new PrivateMethodExposer(theSystem)('printTree)())
//                //val appActorSelection = getApplicationActorSelection(context.system, c.getName)
//                //log debug "appActorSelection: " + appActorSelection
//                val t = (appActorSelection ? (ctx, cls)).mapTo[ResponseEvent[_]]
//                onSuccess(t) { x =>
//                  println("xxx: " + x)
//                  println("xxx: " + x.httpResponse.entity)
//                  println("xxx: " + x.resource)
//                  val list = x.resource.asInstanceOf[List[String]]
//                  complete(list)
//                  //complete(x.httpResponse.copy(entity = x.resource.asInstanceOf[ResponseEntity]))
//                  //complete(x.resource.asInstanceOf[List[_]])
//                }
//              }
//          }
//        }
//      } ~
//      post {
//        entity(as[Order]) { order => // will unmarshal JSON to Order
//          val itemsCount = order.items.size
//          val itemNames = order.items.map(_.name).mkString(", ")
//          complete(s"Ordered $itemsCount items: $itemNames")
//        }
//      }
//}