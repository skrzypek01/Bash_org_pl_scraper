package com.skrzypek01.bashOrgScraper.crawler
import java.nio.ByteBuffer

import com.skrzypek01.bashOrgScraper.crawler.config.Config
import com.skrzypek01.bashOrgScraper.crawler.service.ConnectionServiceImpl
import com.softwaremill.sttp.SttpBackend
import com.softwaremill.sttp.asynchttpclient.monix.AsyncHttpClientMonixBackend
import monix.eval.Task
import monix.execution.Scheduler
import monix.reactive.Observable

trait CompositionRoot {

  def connectionService: ConnectionServiceImpl
}

object CompositionRoot {

  def init(config: Config)(implicit sc: Scheduler): CompositionRoot = new CompositionRoot {
    implicit val sttpBackend: SttpBackend[Task, Observable[ByteBuffer]] = AsyncHttpClientMonixBackend()

    val connectionService = new ConnectionServiceImpl()
    val parssingService = new ParssingServiceImpl()

  }
}
