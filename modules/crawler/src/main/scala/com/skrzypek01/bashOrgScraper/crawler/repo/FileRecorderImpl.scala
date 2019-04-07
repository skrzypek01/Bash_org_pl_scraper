package com.skrzypek01.bashOrgScraper.crawler.repo
import java.io.{File, PrintWriter}

import com.typesafe.config.ConfigFactory
import play.api.libs.json.{JsValue, Json}

import scala.concurrent.{ExecutionContext, Future}

trait FileRecorder {
  def saveResultToFile(result: JsValue): Future[Unit]
}

class FileRecorderImpl(implicit val ec: ExecutionContext) extends FileRecorder {

  def saveResultToFile(result: JsValue): Future[Unit]= {
    val config = ConfigFactory.load()
    val file_destination =
      config.getString("BashOrgScraper.project.destination_filename")
      val writer = new PrintWriter(new File(file_destination))
      writer.write(Json.prettyPrint(result))
      Future.successful(writer.close())
  }
}
