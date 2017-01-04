package com.github.sammyrulez.http4s.entities

/**
  * Created by sam on 04/01/17.
  */
case class Address(state:String,city:String,street:String,number:Int) {

}

object Address {

  val caseSparse = new Address("Italy","Rome","via case sparse",0)

}
