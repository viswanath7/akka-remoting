package com.example.akka.application

import akka.actor.ActorSystem
import com.example.akka.actor.MessagePrinterActor.RequestMessage
import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory

import scala.io.Source


object SeekInteractionClient extends App {

  val logger = LoggerFactory getLogger SeekInteractionClient.getClass
  private val configurationFileName = "seek-client-application.conf"

  private[this] val remoteActorPath = "akka.tcp://RemoteSystem@127.0.0.1:50711/user/remote-message-printer"

  logger debug s"Loading the configuration file $configurationFileName ..."
  private val configuration: Config = ConfigFactory.parseString(Source.fromResource(configurationFileName).mkString)

  logger debug "Creating a client actor system ..."
  private val actorSystem = ActorSystem("SeekClientSystem" , configuration)

  logger debug s"Creating an actor selection for path $remoteActorPath"
  private val actorSelection = actorSystem.actorSelection(remoteActorPath)

  logger debug "Sending a message to the remote actor ..."
  actorSelection ! RequestMessage("Hello from 'seek-interaction-client'")
}
