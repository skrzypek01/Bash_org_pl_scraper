package main.scala

import org.jsoup.Jsoup // Biblioteka do parsowania strony
import java.io.File
import java.io.PrintWriter
import play.api.libs.json._ // Biblioteka do parsowania JSONa

import scala.io.Source
import scala.io.Codec
import com.typesafe.config.ConfigFactory // Typesafe config

/**
  * Główny obiekt Scrapera
  * Start aplikacji z metody main
  */
object Scraper {
  //require(args.length>1)
  /**
    * Trzy klas przypadków definujące specuficzne wyjątki jakie mogą zajść
    *
    * @param message  Wiadomość opsiująca wyjątek
    */
  final case class ZeroNumberException(message: String)
      extends Exception(message)
  final case class IOException(message: String) extends Exception(message)
  final case class ConnectionException(message: String)
      extends Exception(message)

  /**
    * Metoda zapisująca wynik do pliku
    * ścieżka do pliku pobierana jest z pliku konfiguracyjnego
    *
    * @param result Zawartość do zapisania w formacie JSON
    */
  def saveResultToFile(result: JsValue): Unit = {
    val config = ConfigFactory.load()
    val file_destination =
      config.getString("BashOrgScraper.project.destination_filename") // plik docelowy pobierany z application.conf
    try {
      val writer = new PrintWriter(new File(file_destination))
      writer.write(Json.prettyPrint(result)) // metoda prettyPrint "układa" zawartość JsArray do zapisu w pliku tekstowym
      writer.close()
    } catch {
      case ex: Exception => throw new IOException("IO problem occurred")
    }
  }

  /**
    * Metoda wyświetlająca wybrane statystyki działania algorytmu
    *
    * @param n              liczba przetworzonych stron
    * @param records_count  Liczba rekordów
    * @param time_diff      Różnica w czasie [ns]
    */
  def statisticsPrinter(n: Int, records_count: Int, time_diff: Long): Unit = {
    println("Statistics: ")
    println("Number of Records: " + records_count)
    println(
      "Average time of getting record: " + (time_diff / 1000000000.0 / records_count) + " sec")
    println(
      "Average time of getting page: " + (time_diff / 1000000000.0 / n) + " sec")
  }

  /**
    * Metoda parsująca zadaną liczbę stron do przetworzenia podaną jako argument programu
    *
    * @param site_count  Liczba stron w formacie String
    * @return            Liczba stron w formacie Integer
    */
  def getSiteCount(site_count: String): Int = {
    // Regex parsujący tylko liczby całkowite
    // wszelkie inne znaki np '-' są traktowane jako błąd
    val arg_regex = """(\d+)""".r
    site_count match {
      case arg_regex(count) =>
        if (count.toInt > 0)
          count.toInt // Podanie liczby zero traktowane jest jako błąd
        else
          throw new ZeroNumberException("Pages number have to be larger than 0")
      case _ => throw new NumberFormatException("Positive number is needed")
    }
  }

  /**
    * Metoda pobierająca zawartość n zadanych stron serwisu bash.org.pl
    *
    * @param site_number  Liczba podstron do pobrania
    * @return             Zawartość strong w formacie Document z biblioteki JSoup
    */
  def getUrlContenct(site_number: Int): org.jsoup.nodes.Document = {
    implicit val codec = Codec("UTF-8")
    try {
      Jsoup
        .parse(
          Source
            .fromURL(s"http://bash.org.pl/latest/?page=$site_number")
            .mkString)
    } catch {
      case ex: Exception =>
        throw new ConnectionException("Something wrong with Connection")
    }
  }

  /**
    * Metoda przetwarzająca zawartość strony do tablicy zawierającej wpisy
    *
    * @param n  Zadana liczba stron
    * @return   Czas startu algorytmu oraz pobrana zawartość w formacie JsArray z biblioteki play json
    */
  def crawlingMethod(n: Int): (Long, JsArray) = {
    (System.nanoTime(),
     (1 to n) // mapowanie po zbiorze kolejnych liczb całkowitych reprezentujących numery stron
       .map(site_number => {
         getUrlContenct(site_number)
           .getElementById("content") // algorytm zakłada niezmienność w strukturze strony
           .getElementsByClass("q post")
           .toArray
           .map(_.asInstanceOf[org.jsoup.nodes.Element])
           .foldLeft(Json.arr())((arr, element) => { // gromadzenie zawartości stron za pomocą rekurencji
             arr :+ { // aktualizacja wyników tablicy o nowo spasowane elementy
               Json.obj(
                 "id" -> element.id.drop(1).toLong,
                 "points" -> element
                   .getElementsByClass("bar")
                   .select("span.points")
                   .text()
                   .toLong,
                 "content" -> element
                   .getElementsByClass("quote post-content post-body")
                   .text()
               )
             }
           })
       })
       .reduceLeft(_ ++ _)) // połączenie pobranych JsArray w jedno JsArray
  }

  /**
    * Metoda startująca algorytm
    */
  def main(args: Array[String]): Unit = {
    if (args.length > 0) {
      val (start_time, json_records) = crawlingMethod(getSiteCount(args(0))) // Scrapinng
      saveResultToFile(json_records) // Zapis do pliku
      statisticsPrinter(getSiteCount(args(0)), // Wypisanie statystyk
                        json_records.value.size,
                        System.nanoTime() - start_time)
    } else throw new IllegalArgumentException("Number of pages is needed")
  }

}
