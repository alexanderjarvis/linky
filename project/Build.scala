import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "linky"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "org.reactivemongo" %% "reactivemongo" % "0.8",
    "org.reactivemongo" %% "play2-reactivemongo" % "0.8"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
  )

}
