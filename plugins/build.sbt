

sbtPlugin := true
name := "sbt-pipeline"
organization := "org.pipeline"
version := "1.0.0"

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.25")

libraryDependencies += "com.lihaoyi" %% "ujson" % "0.6.7"