val scala3Version = "3.2.2"
val http4sVersion = "0.23.18"

lazy val root = project
  .in(file("."))
  .settings(
    name := "activitypub-scala-exercise",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "org.http4s" %% "http4s-ember-server" % http4sVersion,
      "org.http4s" %% "http4s-ember-client" % http4sVersion,
      "org.http4s" %% "http4s-circe" % http4sVersion,
      "io.circe" %% "circe-literal" % "0.14.3",
      "io.circe" %% "circe-generic" % "0.14.3"
    )
  )
