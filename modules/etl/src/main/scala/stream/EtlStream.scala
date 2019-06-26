package stream
import play.api.libs.json.{JsArray, Json}

class EtlStream {
  def crawlingMethod(n: Int): (Long, JsArray) = ???
//  def crawlingMethod(n: Int): (Long, JsArray) = {
//    (System.nanoTime(),
//      (1 to n) // mapowanie po zbiorze kolejnych liczb całkowitych reprezentujących numery stron
//        .map(site_number => {
//        getUrlContenct(site_number)
//          .getElementById("content") // algorytm zakłada niezmienność w strukturze strony
//          .getElementsByClass("q post")
//          .toArray
//          .map(_.asInstanceOf[org.jsoup.nodes.Element])
//          .foldLeft(Json.arr())((arr, element) => { // gromadzenie zawartości stron za pomocą rekurencji
//            arr :+ { // aktualizacja wyników tablicy o nowo spasowane elementy
//              Json.obj(
//                "id" -> element.id.drop(1).toLong,
//                "points" -> element
//                  .getElementsByClass("bar")
//                  .select("span.points")
//                  .text()
//                  .toLong,
//                "content" -> element
//                  .getElementsByClass("quote post-content post-body")
//                  .text()
//              )
//            }
//          })
//      })
//        .reduceLeft(_ ++ _)) // połączenie pobranych JsArray w jedno JsArray
//  }

}
