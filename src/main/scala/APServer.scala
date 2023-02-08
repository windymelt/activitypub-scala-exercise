import com.comcast.ip4s._
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.ember.server._
import cats.effect.IO
import org.http4s.implicits._
import org.http4s.server.Router
import cats.syntax.all._

final case class APServer():
  val helloWorldService = HttpRoutes.of[IO] {
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name.")
  }
  val apApp = Router("/" -> helloWorldService).orNotFound
  val server = EmberServerBuilder
    .default[IO]
    .withHost(ipv4"0.0.0.0")
    .withHost(ipv6"::")
    .withPort(port"8080")
    .withHttpApp(apApp)
    .build
