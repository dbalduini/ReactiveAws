package io.react2.reactiveaws
package sns

import com.amazonaws.services.sns.model._

/**
 * @author dbalduini
 */
object Protocol {

  case class PushNotification(request: PublishRequest)
  case class CreateEndpoint(request: CreatePlatformEndpointRequest)
  case class DeleteEndpoint(request: DeleteEndpointRequest)

}
