package io.skysail.core.um.domain

import io.skysail.core.domain.ScalaEntity
import io.skysail.core.html.Field
import io.skysail.core.html.InputType.PASSWORD

case class Credentials(
    var id: Option[String] = None,
    //@Size(min = 3, message = "Username must have at least three characters")
    @Field var username: String, 
    
    //@Size(min = 3, message = "Password must have at least three characters")
    @Field(inputType = PASSWORD) var password: String
  ) extends ScalaEntity[String] 