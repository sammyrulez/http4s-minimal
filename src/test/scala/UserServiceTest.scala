import java.time.Instant

import com.github.sammyrulez.http4s.UserService
import com.github.sammyrulez.http4s.entities.User
import com.github.sammyrulez.http4s.jwt.AuthUser
import org.http4s._
import org.http4s.dsl._
import org.scalatest._
import org.http4s.circe._
import io.circe.generic.auto._
import org.http4s.headers.Authorization
import org.http4s.server.middleware.authentication.Authentication
import pdi.jwt.{JwtAlgorithm, JwtCirce, JwtClaim}

/**
  * Created by sam on 04/01/17.
  */
class UserServiceTest extends FlatSpec with Matchers {

  val service = UserService.service

  val claim = JwtClaim(
        expiration = Some(Instant.now.plusSeconds(157784760).getEpochSecond),
        issuedAt = Some(Instant.now.getEpochSecond),
        subject = Some("Sam")
       )
  val token = JwtCirce.encode(claim, AuthUser.key, JwtAlgorithm.HS256)


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
