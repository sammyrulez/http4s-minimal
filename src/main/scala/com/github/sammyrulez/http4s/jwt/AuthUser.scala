package com.github.sammyrulez.http4s.jwt

import com.github.sammyrulez.http4s.entities.User
import org.http4s.headers.Authorization
import org.http4s._
import org.http4s.dsl._
import org.http4s.server._
import org.http4s.util.string._
import scalaz._, Scalaz._, scalaz.concurrent.Task

object AuthUser {


  val authUserKleisli: Kleisli[Task, Request, String \/ User] = Kleisli( request => Task.delay(
     request.headers.get(Authorization) match {
       case None => "NO AUTH".left
       case Some(token) => User(token.toString()).right
     }

  ))

  val onFailure: AuthedService[String] = Kleisli(req => Forbidden(req.authInfo))

  val middleware = AuthMiddleware(authUserKleisli, onFailure)


}


