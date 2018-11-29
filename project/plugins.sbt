//resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.25")
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "latest.integration")

lazy val root = (project in file(".")) dependsOn pipelinePlugin
lazy val pipelinePlugin = ProjectRef(file("../plugins"), "plugins")
addSbtPlugin("org.pipeline" % "sbt-pipeline" % "1.0.0")
