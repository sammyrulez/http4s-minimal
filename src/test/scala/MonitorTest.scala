/**
  * Created by sam on 04/01/17.
  */

import com.github.sammyrulez.http4s.{HelloWorld, MonitorService}
import org.http4s._
import org.http4s.dsl._
import org.scalatest._

class MonitorTest extends FlatSpec with Matchers {

  val service = MonitorService.service

  "A Monitor" should " display metrics for 'HelloWorld' service" in {

    val helloService = HelloWorld.service.toService()

    val serviceRequest = Request(Method.GET, uri("/hello/sam"))
    val serviceTask = helloService.run(serviceRequest)
    val serviceResponse = serviceTask.run


    val getRoot = Request(Method.GET, uri("/"))
    val task = service.run(getRoot)
    val response = task.run
    response.status.code should be (200)

  }




}


