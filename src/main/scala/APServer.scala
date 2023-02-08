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
  val bannerService = HttpRoutes.of[IO] { case GET -> Root =>
    Ok("田舎のサイレンbot")
  }

  val apApp = Router(
    "/" -> bannerService,
    "/.well-known/webfinger" -> Webfinger.webfingerService
  ).orNotFound
  val server = EmberServerBuilder
    .default[IO]
    .withHost(ipv4"0.0.0.0")
    .withHost(ipv6"::")
    .withPort(port"8080")
    .withHttpApp(apApp)
    .build
