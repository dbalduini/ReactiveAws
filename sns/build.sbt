
import Resolvers._

name := "reactiveaws-sns"

organization := "io.react2"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.2"

resolvers ++= Seq(akkaRelease, akkaSnapshot, snapshots, releases)

libraryDependencies ++= Seq(
  "org.tsers.zeison"  %% "zeison"         % "0.5.1" cross CrossVersion.binary
)

