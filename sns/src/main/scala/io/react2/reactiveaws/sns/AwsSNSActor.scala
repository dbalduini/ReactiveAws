package io.react2.reactiveaws
package sns

import core.AwsActor
import com.amazonaws.regions.Region
import com.amazonaws.services.sns.AmazonSNSClient

/**
 * @author dbalduini
 */
class AwsSNSActor(region: Region) extends AwsActor[AmazonSNSClient](region) {

  import Protocol._

  override val client = getClient {
    credentials =>
      new AmazonSNSClient(credentials)
  }

  def receive = {
    case PushNotification(request) =>
      val result = client publish request
      log.debug("SNS::Published::MessageId=" + result.getMessageId)
    case CreateEndpoint(request) =>
      val result = client createPlatformEndpoint request
      log.debug("SNS::Create::EndpointArn=" + result.getEndpointArn)
      sender ! result.getEndpointArn
    case DeleteEndpoint(request) =>
      client deleteEndpoint request
      log.debug("SNS::Delete::EndpointArn=" + request.getEndpointArn)
  }

}
