package io.skysail.core.app.domain

import io.skysail.api.ddd.DddElement

case class Role(name: String)

case class User(
  id: Long,
  username: String,
  roles:List[Role]) extends DddElement

