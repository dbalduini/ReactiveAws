package io.react2.reactiveaws.core

import akka.actor.{Actor, ActorLogging}
import com.amazonaws.AmazonWebServiceClient
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.regions.Region

import scala.util.{Failure, Success}

/**
 * @author dbalduini
 */
abstract class AwsActor[U <: AmazonWebServiceClient](region: Region) extends Actor with ActorLogging {

  @volatile var loaded = false

  def client: U

  protected[reactiveaws] def getClient[T <: AmazonWebServiceClient](f: AWSCredentials => T): T =
    AmazonHelper.credentials match {
      case Success(credentials) =>
        val client = f(credentials)
        loaded = true
        client
      case Failure(t) => throw t
    }

  @scala.throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    client
  }

  @scala.throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    if (loaded) {
      client.shutdown()
      log.warning(" The amazon sns client has been shutdown")
    }
  }

}
