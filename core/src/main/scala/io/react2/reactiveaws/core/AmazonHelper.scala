package io.react2.reactiveaws.core

import com.amazonaws.AmazonClientException
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.profile.ProfileCredentialsProvider

import scala.util.Try
import scala.util.control.NonFatal

/**
 * @author dbalduini
 */
object AmazonHelper {

  def credentials: Try[AWSCredentials] =
    Try(new ProfileCredentialsProvider().getCredentials).recoverWith {
      case NonFatal(t) => throw new AmazonClientException(
        "Cannot load the credentials from the credential profiles file. " +
          "Please make sure that your credentials file is at the correct " +
          "location (~/.aws/credentials), and is in valid format.", t);
    }

}
