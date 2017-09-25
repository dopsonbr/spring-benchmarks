import scala.concurrent.duration._
import io.gatling.core.Predef.{exec, _}
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class OrderSimulation extends Simulation {
  val users = Integer.getInteger("loadTestUsers", 1)
  val ramp = java.lang.Long.getLong("loadTestRamp", 5L)
  val testTime = java.lang.Long.getLong("loadTestTime", 30L)
  val hostUrl = System.getProperty("loadTestHostUrl", "http://localhost:8080")

  val httpProtocol = http
    .baseURL(hostUrl)
    .inferHtmlResources()

  val headers = Map(
    "Connection" -> "keep-alive",
    "accept-encoding" -> "gzip, deflate",
    "cache-control" -> "no-cache")

  val getHeaders = headers ++ Map(
    "Accept" -> "application/json"
  )
  val url = s"${hostUrl}/order"

  val scn = scenario("SyncOrderSimulation").during(testTime seconds) {
      exec(http("BasicOrderCreate")
        .post(url)
        .headers(headers)
        .body(RawFileBody("order.json")))
  }
  setUp(scn.inject(rampUsers(users) over (ramp seconds))).protocols(httpProtocol)
}