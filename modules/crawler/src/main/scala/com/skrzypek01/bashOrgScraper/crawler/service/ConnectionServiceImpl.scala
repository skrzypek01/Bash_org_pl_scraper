package com.skrzypek01.bashOrgScraper.crawler.service

import java.nio.ByteBuffer

import com.skrzypek01.bashOrgScraper.crawler.logic.ConnectionService
import com.softwaremill.sttp._
import monix.eval.Task
import monix.reactive.Observable

class ConnectionServiceImpl(implicit val backend: SttpBackend[Task, Observable[ByteBuffer]]) extends ConnectionService {

  override def getHtml(site_number: Int): Task[List[Response[String]]] = {
    Task.sequence((1 to site_number).map { number =>
      sttp.get(uri"http://bash.org.pl/latest/?page=$number").send()
    }.toList)
  }
}
