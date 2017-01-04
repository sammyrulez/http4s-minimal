package com.github.sammyrulez.http4s.entities

/**
  * Created by sam on 04/01/17.
  */
case class User(username:String,contacts: List[Address]) {

}

object User {

  def apply(username: String, address: Address): User = new User(username, List(address))

  def apply(username: String): User = new User(username, List(Address.caseSparse))


}
