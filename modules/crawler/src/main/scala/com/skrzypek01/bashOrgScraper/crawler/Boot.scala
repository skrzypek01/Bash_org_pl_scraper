package com.skrzypek01.bashOrgScraper.crawler

import akka.actor.ActorSystem
import com.skrzypek01.bashOrgScraper.crawler.config.Config
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

object Boot extends App with LazyLogging {

  logger.info("Starting Bash Org Scraper...")

  implicit val system: ActorSystem = ActorSystem("bashOrgScraper-crawler")
  implicit val ec                  = system.dispatcher

  lazy val config = Config.fromTypesafeConfig(ConfigFactory.load())

  lazy val compositionRoot = CompositionRoot.init(config)

}
