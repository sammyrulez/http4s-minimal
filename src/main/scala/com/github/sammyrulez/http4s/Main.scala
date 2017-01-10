package com.github.sammyrulez.http4s

/**
  * Created by sam on 04/01/17.
  */
import org.http4s.{HttpService, Service}
import org.http4s.rho.swagger.SwaggerSupport
import org.http4s.server.{Server, ServerApp}

import scalaz.concurrent.Task
// import org.http4s.server.{Server, ServerApp}

import org.http4s.server.blaze._
// import org.http4s.server.blaze._



object Main extends ServerApp {

  val hello = HelloWorld.service.toService(SwaggerSupport())
  val apiServices = UserService.service
  val routes = Service.withFallback(hello)(apiServices)

  val service: HttpService = routes.local { req =>
    val path = req.uri.path
    println(s"${req.remoteAddr.getOrElse("null")} -> ${req.method}: $path")
    req
  }

  val monitor = MonitorService.service

  override def server(args: List[String]): Task[Server] = {
    val port = sys.env.getOrElse("PORT", "9000").toInt

    BlazeBuilder.bindHttp(port, "0.0.0.0")
      .mountService(hello, "/api")
      .mountService(HelloWorld.staticService, "/static")
      .mountService(monitor, "/monitor")
      .start
  }
}