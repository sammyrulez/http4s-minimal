import com.github.sammyrulez.http4s.UserService
import com.github.sammyrulez.http4s.entities.User
import org.http4s._
import org.http4s.dsl._
import org.scalatest._
import org.http4s.circe._
import io.circe.syntax._
import io.circe.generic.auto._

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
  it should " save user data" in {
    val req = Request(Method.POST, uri("/user")).withBody(User("Sam"))(jsonEncoderOf)
    val task = service.run(req.run)
    val response = task.run
    response.status.code should be (200)
  }


}
