package skrzypek01.bashOrgScraper.api.service.order

import cats.effect.IO
import io.getquill.SnakeCase
import skrzypek01.bashOrgScraper.api.domain.Order

trait OrderService {

  def sendOrder(order: Order): IO[Unit]
}

class OrderServiceImpl extends OrderService {

  override def sendOrder(order: Order): IO[Unit] = ???

  lazy val ctx = new PostgresJdbcContext(SnakeCase, "ctx")
}
