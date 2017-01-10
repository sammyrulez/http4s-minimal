package com.github.sammyrulez.http4s
import com.github.sammyrulez.http4s.monitor.Metrics
import org.http4s._
import org.http4s.server._
import org.http4s.dsl._
import org.http4s.rho._

import scalaz.concurrent.Task
/**
  * Created by sam on 04/01/17.
  */
object HelloWorld {

  val service = new RhoService {
    GET / "hello" / 'name  |>> { (name: String) =>
      Ok(s"Hello, $name")
    }
  }


  def static(file: String, request: Request) =
    StaticFile.fromResource("/" + file, Some(request)).map(Task.now).getOrElse(NotFound())

  val staticService = HttpService {
    case request @ GET -> Root / path if List(".js", ".css", ".map", ".html", ".webm").exists(path.endsWith) =>
      static(path, request)
  }

}
