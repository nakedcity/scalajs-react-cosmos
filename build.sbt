import sbt.io.IO.copyDirectory


lazy val copyAssets = taskKey[Unit]("Copies assets to webpack'")
lazy val client = project
  .in(file("client"))
  .withId("client")
  .enablePlugins(clientPlugins: _*)
  .settings(
    commonSettings,
    commonClientSettings,
  )

lazy val clientApp = project
  .enablePlugins(clientPlugins: _*)
  .settings(
    webpackDevServerPort := 3000,
    scalaJSUseMainModuleInitializer := true,
    mainClass in Compile := Some("lab.containers.AppContainer"),
  )
  .dependsOn(client)
lazy val clientStories = project
  // we put the results  in a build folder
  .enablePlugins(ScalaJSPlugin, PipelinePlugin)
  .settings(
    scalaJSModuleKind := ModuleKind.CommonJSModule,

    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    mainClass in Compile := Some("lab.containers.AppContainerStory"),
    crossTarget in Compile := (target in client).value / ("scala-" + scalaBinaryVersion.value) / "scalajs-bundler" / "main" / "__fixtures__",

  ).dependsOn(client)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .aggregate(client)


lazy val clientPlugins = List(
  ScalaJSPlugin,
  ScalaJSBundlerPlugin,
  PipelinePlugin
)

lazy val commonSettings = {
  scalaVersion := "2.12.7"
}
lazy val commonClientSettings = Seq(
  name := "client",
  {
    copyAssets := {
      val sourceDir: File = baseDirectory.value / "assets"
      val targetDir: File = target.value / ("scala-" + scalaBinaryVersion.value) / "scalajs-bundler" / "main"
      copyDirectory(sourceDir, targetDir, true)
    }
  },

  scalacOptions += "-P:scalajs:sjsDefinedByDefault",


  //    buildInfoUsePackageAsPath := true,
  libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.6",

  libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "latest.integration",

  //    webpackBundlingMode in fastOptJS := BundlingMode.LibraryAndApplication(),

  (startWebpackDevServer in fastOptJS in Compile) := ((startWebpackDevServer in fastOptJS in Compile)
    dependsOn (dev)
    ).value,

  webpackConfigFile in fastOptJS := Some(baseDirectory.value / "dev.config.js"),

  // Use application model mode for fullOptJS
  //    webpackBundlingMode in fullOptJS := BundlingMode.Application,

  webpackConfigFile in fullOptJS := Some(baseDirectory.value / "prod.config.js"),

  npmDependencies in Compile ++= Seq(
    "react" -> "next",
    "react-dom" -> "next"),

  npmDevDependencies in Compile += "html-webpack-plugin" -> "3.0.6",

  npmDevDependencies in Compile += "webpack-merge" -> "4.1.2",

  npmDevDependencies in Compile += "style-loader" -> "0.21.0",

  npmDevDependencies in Compile += "css-loader" -> "0.28.11",

  npmDevDependencies in Compile += "mini-css-extract-plugin" -> "0.4.0",

  npmDependencies in Compile ++= Seq(
    "react-cosmos" -> "latest",
    "react-cosmos-loader" -> "latest"),


  version in webpack := "4.17.1",

  version in startWebpackDevServer := "3.1.10",
  //    topLevelDirectory := None,

  (fastOptJS in Compile) := ((fastOptJS in Compile) dependsOn (copyAssets)).value,
  (fullOptJS in Compile) := ((fullOptJS in Compile) dependsOn (copyAssets)).value
)



