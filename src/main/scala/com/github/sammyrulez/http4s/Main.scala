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

  val services = HelloWorld.service

  override def server(args: List[String]): Task[Server] = {
    BlazeBuilder
      .bindHttp(8080, "localhost")
      .mountService(services, "/api")
      .start
  }
}