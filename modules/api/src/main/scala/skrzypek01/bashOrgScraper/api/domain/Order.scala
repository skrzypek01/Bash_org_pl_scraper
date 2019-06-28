package skrzypek01.bashOrgScraper.api.domain

import cats.effect.{IO, Sync}
import org.http4s.{EntityDecoder, EntityEncoder}
import io.circe.generic.auto._
import org.http4s.circe._

case class Order(sites: Int)

object Order {

  implicit def orderDecoder: EntityDecoder[IO, Order] = jsonOf[IO, Order]
  implicit def orderEncoder: EntityEncoder[IO, Order] = jsonEncoderOf[IO, Order]
}
