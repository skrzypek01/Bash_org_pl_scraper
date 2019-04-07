package com.skrzypek01.bashOrgScraper.crawler.logic

import com.softwaremill.sttp.Response
import monix.eval.Task

trait ConnectionService {

  def getHtml(site_number: Int): Task[List[Response[String]]]
}
