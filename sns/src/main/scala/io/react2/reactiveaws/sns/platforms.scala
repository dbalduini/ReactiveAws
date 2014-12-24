package io.react2.reactiveaws
package sns

import core.HasConfig

/**
 *
 * Supported platforms by '''SNS Mobile'''.
 *
 * <ul>
 * <li>APNS=Apple Push Notification Service</li>
 * <li>APNS_SANDBOX=Sandbox version of Apple Push Notification Service</li>
 * <li>ADM=Amazon Device Messaging</li>
 * <li>GCM=Google Cloud Messaging</li>
 * <li>BAIDU=Baidu CloudMessaging Service</li>
 * <li>WNS=Windows Notification Service</li>
 * <li>MPNS=Microsoft Push Notificaion Service</li>
 * </ul>
 *
 * @author dbalduini
 */
trait Platform extends HasConfig {
  def name: String
  lazy val applicationArn: String = config.getConfig("aws").getString("sns.applicationArn."+name)
}

case class Apple(alert: String, badge: Int = 9, sound: String = "default", sandbox: Boolean = false) extends Platform {
  override val name = if(sandbox) "APNS_SANDBOX" else "APNS"
}

case class Android(collapseKey: String, data: Data, delayWhileIdle: Boolean = true,
               timeToLive: Int = 125, dryRun: Boolean = false) extends Platform {
  override val name = "GCM"
}

//case class Kindle
//case class Baidu
//case class WNS
//case class MPNS

case class Data(message: String)