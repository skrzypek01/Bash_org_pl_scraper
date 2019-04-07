package com.skrzypek01.bashOrgScraper.crawler

import akka.actor.ActorSystem
import com.skrzypek01.bashOrgScraper.crawler.config.Config
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import monix.execution.Scheduler

import scala.concurrent.ExecutionContext

object Boot extends App with LazyLogging  {

  logger.info("Starting Bash Org Scraper...")

  implicit val system: ActorSystem  = ActorSystem("bashOrgScraper-crawler")
  implicit val ec: ExecutionContext = system.dispatcher
  implicit val sc: Scheduler = Scheduler(ec)

  val config = pureconfig.loadConfigOrThrow[Config](ConfigFactory.load())

  lazy val compositionRoot = CompositionRoot.init(config)
  compositionRoot.connectionService.getHtml(800).runAsyncAndForget

}
