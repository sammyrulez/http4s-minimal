/**
  * Created by sam on 04/01/17.
  */

import com.github.sammyrulez.http4s.HelloWorld
import com.github.sammyrulez.http4s.monitor.Metrics
import org.http4s._
import org.http4s.dsl._
import org.scalatest._

class HelloWorldTest extends FlatSpec with Matchers {

  val service = HelloWorld.service.toService()

  "A HelloWorld" should "say 'hello' to visitors" in {
    val getRoot = Request(Method.GET, uri("/hello/sam"))
    val task = service.run(getRoot)
    val response = task.run
    response.status.code should be (200)

  }

  it should "return 'not found' without someone to say hello" in {
    val getRoot = Request(Method.GET, uri("/hello"))
    val task = service.run(getRoot)
    val response = task.run
    response.status.code should be (404)
  }



}


