package io.skysail.app.demo

final case class AddUserCmd(user: Contact)

// Response objects
final case class UserAddedResp(user: Contact)
final case class UserExistsResp(user: Contact)
