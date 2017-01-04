package com.github.sammyrulez.http4s
import org.http4s._
import org.http4s.server._
import org.http4s.dsl._
/**
  * Created by sam on 04/01/17.
  */
object HelloWorld {
  val service = HttpService {
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name.")
  }

}
