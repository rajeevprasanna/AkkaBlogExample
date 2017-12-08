package blog


import akka.{ImplicitAkkaConfiguration, RestApiServer}
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by rajeevprasanna on 12/8/17.
  */
object Main extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val timeout = Timeout(10.seconds)

  val api = new ImplicitAkkaConfiguration(system, materializer, timeout) with BlogRestApi
  val server = new RestApiServer(api)
  val bindingF = server.bind()

  sys.addShutdownHook{
    import scala.concurrent.ExecutionContext.Implicits.global

    val afterSystemTerminates =
      for {
        binding <- bindingF
        _ <- binding.unbind()
        _ <- system.terminate()
      } yield ()

    Await.result(afterSystemTerminates, 10.seconds)
  }

  ()
}
