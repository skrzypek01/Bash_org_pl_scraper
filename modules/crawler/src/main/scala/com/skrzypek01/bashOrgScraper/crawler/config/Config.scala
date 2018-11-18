package com.skrzypek01.bashOrgScraper.crawler.config

case class Config(conf: String)

object Config {

  def fromTypesafeConfig(config: com.typesafe.config.Config): Config = ???

}
