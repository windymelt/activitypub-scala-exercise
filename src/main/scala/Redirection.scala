import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.headers.Location
import org.http4s.syntax.all._

object Redirection {
  val redirectionService = HttpRoutes.of[cats.effect.IO] {
    case GET -> Root / "@"     => Found(Location(uri"/"))
    case GET -> Root / "u"     => Found(Location(uri"/"))
    case GET -> Root / "user"  => Found(Location(uri"/"))
    case GET -> Root / "users" => Found(Location(uri"/"))
  }
}
