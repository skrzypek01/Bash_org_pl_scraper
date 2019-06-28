package skrzypek01.bashOrgScraper.api.web

import cats.effect.{IO, Sync}
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import skrzypek01.bashOrgScraper.api.domain.Order
import skrzypek01.bashOrgScraper.api.service.order.OrderService

class OrderEndpoints(orderService: OrderService) extends Http4sDsl[IO] {

  import Order._

  private def orderScrapEndpoint(orderService: OrderService): HttpRoutes[IO] =
    HttpRoutes.of[IO] {
    case req @ POST -> Root =>
     val action =  for {
      order <- req.as[Order]
     result <- orderService.sendOrder(order)
      } yield result
  }

  private def checkOrderEndpoint(orderService: OrderService): HttpRoutes[IO] = {
    case req @ POST -> Root =>
      Ok("hello")
  }

  def endpoints: HttpRoutes[IO] = {
    orderScrapEndpoint(orderService) <+> checkOrderEndpoint(orderService)
  }



}



object OrderEndpoints {
def endpoints(orderService: OrderService): HttpRoutes[IO] = new OrderEndpoints(orderService).endpoints
}
