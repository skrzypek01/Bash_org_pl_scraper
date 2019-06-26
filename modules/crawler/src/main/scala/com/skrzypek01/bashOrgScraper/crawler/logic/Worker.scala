package com.skrzypek01.bashOrgScraper.crawler.logic

import org.jsoup.Jsoup
import java.io.{File, PrintWriter}

import com.typesafe.config.ConfigFactory
import play.api.libs.json.{JsArray, JsValue, Json}

import scala.concurrent.{ExecutionContext, Future}
import scala.io.{Codec, Source}

class Worker(implicit val ec: ExecutionContext) {

  def getSiteCount(site_count: String): Int = {
    // Regex parsujący tylko liczby całkowite
    // wszelkie inne znaki np '-' są traktowane jako błąd
    val arg_regex = """(\d+)""".r
    site_count match {
      case arg_regex(count) =>
        if (count.toInt > 0)
          count.toInt // Podanie liczby zero traktowane jest jako błąd
        else
          0
    }
  }

  def getUrlContenct(site_number: Int): Future[Seq[org.jsoup.nodes.Document]] = {
    val sites          = (0 to site_number)
    implicit val codec = Codec("UTF-8")

    Future { sites.map { site_num =>
      Jsoup
        .parse(
          Source
            .fromURL(s"http://bash.org.pl/latest/?page=$site_num")
            .mkString)
    }}
  }



//  def main(args: Array[String]): Unit = {
//    if (args.length > 0) {
//      val (start_time, json_records) = crawlingMethod(getSiteCount(args(0))) // Scrapinng
//      saveResultToFile(json_records) // Zapis do pliku
//      statisticsPrinter(getSiteCount(args(0)), // Wypisanie statystyk
//                        json_records.value.size,
//                        System.nanoTime() - start_time)
//    } else throw new IllegalArgumentException("Number of pages is needed")
//  }

}
