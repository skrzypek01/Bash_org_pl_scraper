# Bash_org_pl_scraper

Scraper pobierający wpisy z serwisu bash.org.pl. Scraper pobiera N najnowszych wpisów i parsuje je do foramtu JSON (JSON Array):
```
[{
  "id": x,
  "points": y,
  "content: z
},
{
  "id": x,
  "points": y,
  "content: z
}]
```
Output serwowany jest do pliku, który konfigurowany jest z pliku application.conf, N jest parametrem wejściowym programu. Zakończeniem programu jest wyświetlenie na konsole trzech statystyk:
* Liczba pobranych wpisów
* Średni czas pozyskania jednego wpisu
* Średni czas pozyskania jednej strony

## Założenia

* Wpisy są zapisywane jako JSON Array, ponieważ wtedy w łatwy spsób można je odczytać z pliku z powrotem w format JSON
* Plik jest nadpisywany z każdym uruchomieniem programu
* Do pozyskania zawartości strony bash.org.pl użyto zewnętrznej biblioteki

## Użyte biblioteki

* [JSoup](https://jsoup.org/) - Parser HTML
* [Play JSON](https://www.playframework.com/documentation/2.6.x/ScalaJson) - Parser JSON
* [Typesafe Config](https://github.com/lightbend/config) - Biblioteka konfiguracji

## Ogólny schemat działania aplikacji

1. Sparsowanie argumentu aplikacji do postaci liczby całkowitej.
2. Dla każdej strony pobranie zawartości i parsowanie jej za pomocą biblioteki Jsoup.
3. Wyodrębnienie zawartości strony zawierającej wpisy użytkowników.
4. Transformacja zawartości do typu danych Array o elementach typu ```org.jsoup.nodes.Element```.
5. Parsowanie każdego wpisu do formatu JSON Object i scalanie wpisów z jednej strony do formatu JSON Array.
    Wyodrębnianie są trzy konkretne pola identyfikowane na podstawie struktury serwisu bash.org.pl.
6. Scalenie wpisów dla każdej strony w jeden wynikowy JsArray.
7. Zapisanie uzyskanego wyniku do pliku o nazwie i ścieżce pobranej z application.conf.
8. Wyświetlenie prostych statystyk do konsoli.
9. Zakończenie działania aplikacji.

## Uwagi

* Aplikację wytwarzano z wykorzystaniem środowiska Intellij IDEA
