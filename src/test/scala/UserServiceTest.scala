import com.github.sammyrulez.http4s.UserService
import com.github.sammyrulez.http4s.entities.User
import org.http4s._
import org.http4s.dsl._
import org.scalatest._
import org.http4s.circe._
import io.circe.generic.auto._
import org.http4s.headers.Authorization
import org.http4s.server.middleware.authentication.Authentication

/**
  * Created by sam on 04/01/17.
  */
class UserServiceTest extends FlatSpec with Matchers {

  val service = UserService.service

  val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ"

  "A User repository " should " return user data" in {
    val getRoot = Request(Method.GET, uri("/user/sam")).putHeaders(Header(Authorization.name.value,token))
    val task = service.run(getRoot)
    val response = task.run
    response.status.code should be (200)

  }
  it should " save user data" in {
    val req = Request(Method.POST, uri("/user")).putHeaders(Header(Authorization.name.value,token)).withBody(User("Sam"))(jsonEncoderOf)
    val task = service.run(req.run)
    val response = task.run
    response.status.code should be (200)
  }


}
