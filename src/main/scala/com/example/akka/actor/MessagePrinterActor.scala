package com.example.akka.actor

import akka.actor.{Actor, Props}
import com.example.akka.actor.MessagePrinterActor.RequestMessage
import org.slf4j.LoggerFactory

object MessagePrinterActor {
  val props = Props[MessagePrinterActor]
  sealed trait Message
  case class RequestMessage(content: String) extends Message
}

/**
  * Actor that logs content of messages that it receives.
  * It only supports message of type 'RequestMessage' and rest is ignored.
  */
class MessagePrinterActor extends Actor {
  val logger = LoggerFactory getLogger MessagePrinterActor.getClass

  override def receive: Receive = {
    case  RequestMessage(content) =>
      logger debug s"$self: received message '$content'"
    case _ => logger warn s"$self received an unsupported message"
  }
}
