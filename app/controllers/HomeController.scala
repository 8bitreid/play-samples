package controllers

import example.myapp.helloworld.grpc.GreeterServiceClient
import example.myapp.helloworld.grpc.HelloReply
import example.myapp.helloworld.grpc.HelloRequest
import javax.inject.Inject
import akka.stream.Materializer
import play.api.mvc._

import scala.concurrent.Future

class HomeController @Inject() (mat: Materializer, greeterServiceClient: GreeterServiceClient) extends InjectedController {
  implicit val ec = mat.executionContext

  def index = Action.async {
    val request = HelloRequest("Caplin")
    val reply: Future[HelloReply] = greeterServiceClient.sayHello(request)

    reply.map(_.message).map(m ⇒ Ok(m))
  }

  def ping = Action {
    Ok("pong")
  }
}
