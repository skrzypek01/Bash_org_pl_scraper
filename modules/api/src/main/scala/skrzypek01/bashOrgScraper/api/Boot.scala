package skrzypek01.bashOrgScraper.api

import cats.effect._
import cats.implicits._
import org.http4s.server.{Router, Server}
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import skrzypek01.bashOrgScraper.api.web.OrderEndpoints

object Boot extends IOApp {

  def createServer: Resource[IO, Server[IO]] = {

    val orderService = ???

    val webApp = Router(
      "/order" -> OrderEndpoints.endpoints(orderService)
    ).orNotFound

    val server = BlazeServerBuilder[IO].bindHttp(1111, "127.0.0.1").withHttpApp(webApp).resource
    server
  }

  override def run(args: List[String]): IO[ExitCode] = createServer.use(_ => IO.never).as(ExitCode.Success)
}
