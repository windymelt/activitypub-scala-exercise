import cats.syntax.all._
import io.circe._
import io.circe.literal._
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.ember.server._
import org.http4s.implicits._
import org.http4s.circe._
import io.circe.generic.auto._

object User:
  val userName = "inaka_siren_bot"
  val hostname = "siren.capslock.dev"

  val getUserService = HttpRoutes.of[cats.effect.IO] {
    case req @ GET -> Root / "u" / `userName` =>
      req.headers.get(org.typelevel.ci.CIString("")) match
        case None => plainUserInfo
        case Some(headers) =>
          headers.exists(kv =>
            kv.value == "application/activity+json" || kv.value == "*/*"
          ) match
            case true  => Ok()
            case false => plainUserInfo
  }

  val plainUserInfo = Ok(s"$userName: 田舎のサイレンbot")
