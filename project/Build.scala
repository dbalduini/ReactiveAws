import sbt._
import Keys._

object Build extends Build
{

  // Root
  lazy val root = (project in file(".")).aggregate(core, sns)
  // Modules
  lazy val core = project in file("core")
  lazy val sns  =  project in file("sns") dependsOn core

}

object Resolvers {
  val akkaRelease = "typesafe release repo" at "http://repo.typesafe.com/typesafe/releases/"
  val akkaSnapshot = "typesafe snapshot repo" at "http://repo.typesafe.com/typesafe/snapshots/"
  val snapshots = "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
  val releases = "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
}