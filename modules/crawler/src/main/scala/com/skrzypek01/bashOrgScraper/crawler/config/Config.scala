package com.skrzypek01.bashOrgScraper.crawler.config

import com.typesafe.config.{Config => TypesafeConfig}

final case class FilePathConfig(filePath: String)

case class Config(filePathConfig: FilePathConfig)
