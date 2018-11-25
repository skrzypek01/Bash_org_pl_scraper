import sbt.internal.IvyConsole

name := "BashOrgScraper"

version := "0.1"

scalaVersion := "2.12.5"

libraryDependencies += "org.jsoup"         % "jsoup"          % "1.11.2"
libraryDependencies += "com.typesafe.play" %% "play-json"     % "2.6.9"
libraryDependencies += "com.typesafe"      % "config"         % "1.3.3"
libraryDependencies += "org.scalatest"     % "scalatest_2.12" % "3.0.4" % "test"

lazy val GlobalScalaVersion = "2.12.6"

scalaVersion := GlobalScalaVersion

val ItTest = config("it") extend (Test)

val projectVersion = "0.0.1"

lazy val root = Project(id = "bashOrgScraper", base = file("."))
  .aggregate(api, crawler, etl)
  .settings(commonSettings)

val commonSettings = Seq(
  organization := "com.skrzypek01",
  scalaVersion := GlobalScalaVersion,
  scalafmtOnCompile := true,
  excludeDependencies := Seq("org.slf4j" % "slf4j-log4j12"),
  libraryDependencies ++=
    Dependencies.akkaHttp
      ++ Dependencies.scalaLogging
      ++ Dependencies.scalaTest
      ++ Dependencies.typesafeConfig
      ++ Dependencies.playJson
      ++ Dependencies.slickLibs
      ++ Dependencies.jsoupLib
)

lazy val api = Project(id = "bashOrgSraper-api", base = file("modules/api"))
  .settings(commonSettings)
  .configs(ItTest)
  .settings(inConfig(ItTest)(Defaults.itSettings))
  .settings(
    name := "bashOrgScraper-api",
    version := projectVersion,
    parallelExecution in Test := false
  )
  .dependsOn(common)

lazy val crawler = Project(id = "bashOrgScraper-crawler", base = file("modules/crawler"))
  .settings(commonSettings)
  .configs(ItTest)
  .settings(inConfig(ItTest)(Defaults.itSettings))
  .settings(
    name := "bashOrgScraper-crawler",
    version := projectVersion,
    parallelExecution in Test := false
  )
  .dependsOn(common)

lazy val etl = Project(id = "bashOrgScraper-etl", base = file("modules/etl"))
  .settings(commonSettings)
  .configs(ItTest)
  .settings(inConfig(ItTest)(Defaults.itSettings))
  .settings(
    name := "bashOrgScraper-etl",
    version := projectVersion,
    parallelExecution in Test := false
  )
  .dependsOn(common)

lazy val common = Project(id = "bashOrgScraper-common", base = file("modules/common"))
  .settings(commonSettings)
  .configs(ItTest)
  .settings(inConfig(ItTest)(Defaults.itSettings))
  .settings(
    name := "bashOrgScraper-common",
    version := projectVersion,
    parallelExecution in Test := false
  )
