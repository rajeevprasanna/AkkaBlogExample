package akka

import akka.actor.ActorRefFactory
import akka.stream.Materializer
import akka.util.Timeout

import scala.concurrent.ExecutionContext

/**
  * Created by rajeevprasanna on 12/8/17.
  */

/**
  * Mix-in for pulling in Akka-related classes and configuration.
  */
trait AkkaConfiguration {
  implicit val actorRefFactory:ActorRefFactory
  implicit val materializer:Materializer
  implicit val executionContext:ExecutionContext = actorRefFactory.dispatcher
  implicit val askTimeout :Timeout
}


/**
  * Implicit-based implementation of AkkaConfiguration. Given a trait `Foo` which mixes in `AkkaConfiguration`, a
  * concrete instance of `Foo` can be created by injecting implicitly-available instances like so:
  *
  * {{{
  *   implicit val system = ActorSystem()
  *   implicit val materializer = ActorMaterializer()
  *   implicit val timeout = Timeout(30.seconds)
  *   val foo = new ImplicitAkkaConfiguration with Foo
  * }}}
  */
class ImplicitAkkaConfiguration(override val actorRefFactory: ActorRefFactory,
                                override val materializer: Materializer,
                                override val askTimeout: Timeout) extends AkkaConfiguration
