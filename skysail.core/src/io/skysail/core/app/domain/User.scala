package io.skysail.core.app.domain

case class Role(name: String)

case class User(
  id: Long,
  username: String,
  roles:List[Role])

