package com.github.sammyrulez.http4s

import com.github.sammyrulez.http4s.entities.User
import org.http4s._
import org.http4s.dsl._
import org.http4s.circe._
import io.circe.generic.auto._
import io.circe.syntax._

/**
  * Created by sam on 04/01/17.
  */
object UserService {


  val jsonService = HttpService {
    case r @ POST -> Root / "user" =>
      r.as(jsonOf[User]).flatMap(user =>
        Ok(User.save(user).isSuccess.asJson)
      )
  }

  val service = HttpService {
    case GET -> Root / "user" / name =>
      Ok(User(name).asJson)
  }
}

