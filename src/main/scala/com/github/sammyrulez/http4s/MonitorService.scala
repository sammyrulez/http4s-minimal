package com.github.sammyrulez.http4s

import com.github.sammyrulez.http4s.monitor.Metrics
import org.http4s._
import org.http4s.dsl._

/**
  * Created by sam on 10/01/17.
  */
object MonitorService {


  val service =  HttpService {
    case GET -> Root  =>
      Ok(Metrics.defaultRegistry.outputText)
  }

}
