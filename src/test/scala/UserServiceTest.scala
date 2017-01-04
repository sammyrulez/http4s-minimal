import com.github.sammyrulez.http4s.UserService
import org.http4s._
import org.http4s.dsl._
import org.scalatest._

/**
  * Created by sam on 04/01/17.
  */
class UserServiceTest extends FlatSpec with Matchers {

  val service = UserService.service

  "A User repository " should " return user data" in {
    val getRoot = Request(Method.GET, uri("/user/sam"))
    val task = service.run(getRoot)
    val response = task.run
    response.status.code should be (200)

  }
}
