package com.skrzypek01.bashOrgScraper.crawler
import com.skrzypek01.bashOrgScraper.crawler.config.Config

import scala.concurrent.ExecutionContext

abstract class CompositionRoot private (val config: Config) {}

object CompositionRoot {

  def init(config: Config)(implicit ec: ExecutionContext): CompositionRoot = new CompositionRoot(config) {}
}
