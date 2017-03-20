package com.example.akka.application

import akka.actor.ActorSystem
import com.example.akka.actor.MessagePrinterActor
import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory

import scala.io.Source

object RemoteSystem extends App {

  val logger = LoggerFactory getLogger RemoteSystem.getClass
  private val configurationFileName = "remote-application.conf"

  logger debug s"Loading the configuration file $configurationFileName ..."
  private val configuration: Config = ConfigFactory.parseString(Source.fromResource(configurationFileName).mkString)

  logger debug "Creating a remote actor system ..."
  private val actorSystem = ActorSystem("RemoteSystem" , configuration)

  logger debug "Creating the remote actor ..."
  private val remoteActor = actorSystem.actorOf(MessagePrinterActor.props, "remote-message-printer")

  logger debug s"Path of the remote actor: ${remoteActor.path}"

}
