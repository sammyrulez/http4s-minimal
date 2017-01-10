package com.github.sammyrulez.http4s

import com.github.sammyrulez.http4s.entities.User
import com.github.sammyrulez.http4s.jwt.AuthUser
import org.http4s._
import org.http4s.dsl._
import org.http4s.circe._
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.server.AuthMiddleware

import scalaz.Kleisli
import scalaz.concurrent.Task

/**
  * Created by sam on 04/01/17.
  */
object UserService {



  val authedService: AuthedService[User] =
    AuthedService {
     case GET -> Root / "user" / name  as loggedInUser => Ok(User(name).asJson)
     case r @ POST -> Root / "user"  as loggedInUser  =>
        //r.as(jsonOf[User]).flatMap(user =>

          Ok(User.save(loggedInUser).isSuccess.asJson)


    }

  val service: HttpService = AuthUser.middleware(authedService)


}

