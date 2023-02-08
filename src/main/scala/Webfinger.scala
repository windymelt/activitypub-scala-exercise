import cats.syntax.all._
import io.circe._
import io.circe.literal._
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.ember.server._
import org.http4s.implicits._
import org.http4s.circe._
import io.circe.generic.auto._

private object ResourceMatcher
    extends QueryParamDecoderMatcher[String]("resource")

private case class WebfingerResult(
    subject: String,
    aliases: List[String],
    links: List[WebfingerLink]
)

private case class WebfingerLink(
    rel: String,
    `type`: String,
    href: String
)

/** Webfinger response endpoint. Almost fixed response.
  */
object Webfinger:
  val webfingerService = HttpRoutes.of[cats.effect.IO] {
    case GET -> Root :? ResourceMatcher(
          s"acct:${strName @ User.userName}@${strHost @ User.hostname}"
        ) =>
      val res = WebfingerResult(
        s"acct:${strName}@${strHost}",
        List(
          s"https://${strHost}/@${strName}",
          s"https://${strHost}/u/${strName}",
          s"https://${strHost}/user/${strName}",
          s"https://${strHost}/users/${strName}"
        ),
        List(
          WebfingerLink(
            "self",
            "application/activity+json",
            s"https://${strHost}/u/${strName}"
          )
        )
      )

      import io.circe.syntax._
      Ok(res.asJson)
    case GET -> Root =>
      BadRequest(json"""{"error": "requires 'resource' query parameter."}""")
  }
