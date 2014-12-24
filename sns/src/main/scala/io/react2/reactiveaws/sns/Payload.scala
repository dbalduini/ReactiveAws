package io.react2.reactiveaws
package sns

import org.tsers.zeison.{Zeison => json}

/**
 * @author dbalduini
 */
object Payload {

  import scala.annotation.implicitNotFound

  @implicitNotFound("No member of type class PayloadLike in scope for ${T}")
  trait PayloadLike[T <: Platform] {

    /*
    * This message is delivered if a platform specific message is not specified
    * for the end point. It must be set. It is received by the device as the
    * value of the key "default".
    */
    val defaultMessage = "This is the default Message"

    protected [this] def withDefault(platform: T, payload: json.JValue): String = {
      // the payload must be encoded as a String
      val message = json.obj("default" -> defaultMessage, platform.name -> json.render(payload))
      json renderPretty message
    }

    def jsonify(p: T): String
  }

  object PayloadLike {

    /**
     * Handle Apple Push Notification Service.
     */
    implicit object PayloadLikeApple extends PayloadLike[Apple] {
      override def jsonify(p: Apple): String = {
        val payload = json.obj(
          "aps" -> json.obj(
            "alert" -> p.alert,
            "badge" -> p.badge,
            "sound" -> p.sound))
        withDefault(p, payload)
      }
    }

    /**
     * Handle Google Cloud Messaging for Android.
     */
    implicit object PayloadLikeAndroid extends PayloadLike[Android] {
      override def jsonify(p: Android): String = {
        val payload = json.obj(
          "collapse_key" -> p.collapseKey,
          "data" -> json.obj(
            "message" -> p.data.message
          ),
          "delay_while_idle" -> p.delayWhileIdle,
          "time_to_live" -> p.timeToLive,
          "dry_run" -> p.dryRun
        )
        withDefault(p, payload)
      }
    }

  }

}

