package akka

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives

/**
  * Created by rajeevprasanna on 12/8/17.
  */

trait RestApi extends Directives with JsonSupport {
    def route:Route
}