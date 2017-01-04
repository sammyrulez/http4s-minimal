package com.github.sammyrulez.http4s

/**
  * Created by sam on 03/01/17.
  */
import org.http4s.server.blaze.BlazeBuilder
import sun.jvm.hotspot.HelloWorld

object BlazeExample extends App {
  BlazeBuilder.bindHttp(8080)
    .mountService(HelloWorld.service, "/")
    .run
    .awaitShutdown()
}
