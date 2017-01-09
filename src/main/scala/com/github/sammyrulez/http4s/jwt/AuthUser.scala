package com.github.sammyrulez.http4s.jwt

import com.github.sammyrulez.http4s.entities.User
import org.http4s.headers.Authorization
import org.http4s._
import org.http4s.dsl._
import org.http4s.server._
import org.http4s.util.string._
import pdi.jwt.{JwtCirce, JwtAlgorithm, JwtClaim}

import scalaz._
import Scalaz._
import scalaz.concurrent.Task

object AuthUser {

  val key = "secret"

  val authUserKleisli: Kleisli[Task, Request, String \/ User] = Kleisli( request => Task.delay(

    for { token <- request.headers.get(Authorization) \/> "NO AUTH"
          claim <- JwtCirce.decode(token.toRaw.value, key, Seq(JwtAlgorithm.HS256)).toOption \/> "INVALID TOKEN"
          subject <- claim.subject  \/>  "NO SUBJ"
          user <- User(subject).right
    } yield user

  ))

  val onFailure: AuthedService[String] = Kleisli(req => Forbidden(req.authInfo))

  val middleware = AuthMiddleware(authUserKleisli, onFailure)


}


