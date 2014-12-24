package io.react2.reactiveaws.core

import com.typesafe.config.ConfigFactory

/**
 * @author dbalduini
 */
trait HasConfig {
  lazy val config = ConfigFactory.load()
}
