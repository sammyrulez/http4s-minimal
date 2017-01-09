package com.github.sammyrulez.http4s

/**
  * Created by sam on 04/01/17.
  */
import org.http4s.server.{Server, ServerApp}

import scalaz.concurrent.Task
// import org.http4s.server.{Server, ServerApp}

import org.http4s.server.blaze._
// import org.http4s.server.blaze._



object Main extends ServerApp {

  val hello = HelloWorld.service

  val servicies = UserService.service

  override def server(args: List[String]): Task[Server] = {
    val port = sys.env.getOrElse("PORT", "9000").toInt

    BlazeBuilder.bindHttp(port, "0.0.0.0")
      .mountService(hello, "/common")
      .mountService(servicies, "/api")
      .start
  }
}