import com.github.sammyrulez.http4s.Main
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by sam on 04/01/17.
  */
class MainTest extends FlatSpec with Matchers{

  "A server" should "start" in {
    Main.server(List())
  }

}
