package main.scala
import org.scalatest.FunSuite
import Scraper.ZeroNumberException

class ScraperTest extends FunSuite {

  test("Scraper.getSiteCount") {
    assert(Scraper.getSiteCount("1") === 1)
    assert(Scraper.getSiteCount("12") === 12)
    assertThrows[NumberFormatException] {
      Scraper.getSiteCount("23a")
    }
    assertThrows[NumberFormatException] {
      Scraper.getSiteCount(".&t")
    }
    assertThrows[ZeroNumberException] {
      Scraper.getSiteCount("0")
    }
  }

}
