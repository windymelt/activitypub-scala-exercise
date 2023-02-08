import cats.effect._

object Main extends IOApp:
  def run(args: List[String]): IO[ExitCode] =
    APServer().server
      .use(_ => IO.never)
      .as(ExitCode.Success)
