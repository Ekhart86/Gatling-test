//BasicSimulation.scala
package test

/*
Необходимые библиотеки для работы:
io.gatling.core.Predef._ - функции ядра
import io.gatling.http.Predef._ - функции HTTP
import scala.concurrent.duration._ - функции для временных интервалов, чтобы можно было писать `4 minutes`, `15 seconds`
*/
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

/*
Основной класс теста.
Именно этот класс, расширяемый от Simulation, ищет фреймворк во время запуска.
*/
class BasicSimulation extends Simulation {

  /*
  Настройки для HTTP
  */
  val httpConf = http
    .baseURL("http://computer-database.gatling.io")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  /*
  Сценарий скриптов. Здесь в виде "цепочки" пишем запросы, таймеры, отчеты (pacing) и все остальное связанное со сценарием скриптов.
  */
  val scn = scenario("BasicSimulation")
    .exec(
      http("GETRequest")
        .get("http://istqb-training.ru/")
        .check(status.is(200))
        .check(substring("Григорий Гриб"))
    )


  /*
  Сценарий нагрузки. Здесь указываем характер генерируемой нагрузки, модель поднятия пользователей, их количество и длительность нагрузки.
  */
  setUp(
    scn.inject(atOnceUsers(100))
  ).protocols(httpConf)
}