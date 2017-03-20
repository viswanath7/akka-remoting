package com.example.akka.application

import akka.actor.ActorSystem
import com.example.akka.actor.MessagePrinterActor
import com.example.akka.actor.MessagePrinterActor.RequestMessage
import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory

import scala.io.Source


object CreateInteractionClient extends App {

  val logger = LoggerFactory getLogger CreateInteractionClient.getClass
  private val configurationFileName = "create-client-application.conf"

  logger debug s"Loading the configuration file $configurationFileName ..."
  private val configuration: Config = ConfigFactory.parseString(Source.fromResource(configurationFileName).mkString)

  logger debug "Creating a client actor system ..."
  private val actorSystem = ActorSystem("CreateClientSystem" , configuration)

  logger debug "Create a client for remote actor"
  private val remoteMessagePrinter = actorSystem.actorOf(MessagePrinterActor.props, "non-local-message-printer")
  logger debug s"Path of the remote actor: ${remoteMessagePrinter.path}"

  logger debug "Sending a message to remote actor"
  remoteMessagePrinter ! RequestMessage("Hello from 'create-interaction-client'")
}
