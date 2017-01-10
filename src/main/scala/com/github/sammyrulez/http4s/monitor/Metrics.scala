package com.github.sammyrulez.http4s.monitor

import java.time._
import java.util.Date
import java.util.concurrent.TimeUnit

import org.http4s._
import org.lyranthe.prometheus.client._

import scalaz.concurrent.Task

/**
  * Created by sam on 09/01/17.
  */
object Metrics {

  val httpSystem = "http"

  val requestSubsystem = "requests"

  implicit val defaultRegistry = DefaultRegistry()

  implicit val histogramBuckets = HistogramBuckets(1, 2, 5, 10, 20, 50, 100)

  lazy val activeRequests = Gauge(metric"active_requests", "Active requests").labels().register

  lazy val requestLatency = Histogram(metric"request_latency", "Request latency").labels(label"path").register

  lazy val numErrors = Counter(metric"num_errors", "Total errors").labels().register


  //TODO configuration


  private def myMiddle(service: HttpService): HttpService = Service.lift { req =>
    activeRequests.inc()

    val startTime = new Date()
    service(req).map {resp =>
      Task.now({
        activeRequests.dec()
        if (resp.status.isSuccess)
          requestLatency.labelValues(req.uri.renderString).observe(dateDiff(startTime))
        else
          numErrors.inc()

      })
      resp
    }
  }


  private def dateDiff( date1:Date)  = (new Date().getTime - date1.getTime()).toDouble

  def apply(service: HttpService) = myMiddle(service)



}
