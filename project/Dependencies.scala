import sbt._

object Dependencies {

  lazy val akkaHttp = Seq(
    "com.typesafe.akka" %% "akka-http" % VersionsOf.`akka-http`,
    "de.heikoseeberger" %% "akka-http-play-json" % VersionsOf.`akka-http-play-json`,
    "com.typesafe.akka" %% "akka-http-testkit" % VersionsOf.`akka-http` % Test
  )

  lazy val scalaLogging = Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % VersionsOf.`scala-logging`
  )

  lazy val typesafeConfig = Seq(
    "com.typesafe" % "config" % VersionsOf.`typesafe-config`
  )

  lazy val scalaMock = Seq("org.scalamock" %% "scalamock" % VersionsOf.scalamock)

  lazy val scalaTest = Seq(
    "org.scalatest" %% "scalatest" % VersionsOf.scalatest
  )

  lazy val playJson = Seq(
    "com.typesafe.play" %% "play-json" % VersionsOf.`play`
  )

  lazy val slickLibs = Seq(
    "com.typesafe.slick" %% "slick" % VersionsOf.slick
  )

  lazy val jsoupLib = Seq(
    "org.jsoup" %  "jsoup" % VersionsOf.jsoup
  )
  
  lazy val pureConfig = Seq(
    "com.github.pureconfig" %% "pureconfig" % VersionsOf.pureconfig
  )

  lazy val akkaStreams = Seq(
    "com.typesafe.akka" %% "akka-stream" % VersionsOf.akkaStream
  )

  lazy val sttp = Seq(
    "com.softwaremill.sttp" %% "core" % VersionsOf.sttp,
    "com.softwaremill.sttp" %% "async-http-client-backend-monix" % VersionsOf.sttp,
    "com.softwaremill.sttp" %% "async-http-client-backend-future" % VersionsOf.sttp
  )

  lazy val monix = Seq(
    "io.monix" %% "monix" % VersionsOf.monix
  )

  lazy val http4s = Seq(
    "org.http4s" %% "http4s-blaze-server" % VersionsOf.http4s,
    "org.http4s" %% "http4s-circe" % VersionsOf.http4s,
    "org.http4s" %% "http4s-dsl" % VersionsOf.http4s
  )
  
  lazy val circe = Seq(
    "io.circe"              %% "circe-generic"          % VersionsOf.circe,
    "io.circe"              %% "circe-literal"          % VersionsOf.circe,
    "io.circe"              %% "circe-generic-extras"   % VersionsOf.circe,
    "io.circe"              %% "circe-parser"           % VersionsOf.circe
  )
  
  lazy val quill = Seq(
    "io.getquill" %% "quill-async" % VersionsOf.quill
  )
}
