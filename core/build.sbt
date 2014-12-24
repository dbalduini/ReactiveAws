
import Resolvers._

name := "reactiveaws-core"

organization := "io.react2"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.2"

resolvers ++= Seq(akkaRelease, akkaSnapshot, snapshots, releases)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor"   % "2.3.8"           cross CrossVersion.binary,
  "com.typesafe.akka" %% "akka-testkit" % "2.3.8"  % "test" cross CrossVersion.binary,
  "com.amazonaws"      % "aws-java-sdk" % "1.8.4",
  "com.typesafe"       % "config"       % "1.2.1",
  "org.scalatest"     %% "scalatest"    % "2.2.1"  % "test"
)
