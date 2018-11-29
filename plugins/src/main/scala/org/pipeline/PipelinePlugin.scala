package org.pipeline

import java.nio.file._

import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import org.scalajs.sbtplugin._
import sbt._

import scala.io._
import sbt.plugins.JvmPlugin


object PipelinePlugin extends AutoPlugin {
  object autoImport {
    val dev = sbt.TaskKey[Unit]("dev", "Prepare source maps")
    val devy = sbt.SettingKey[File]("devy", "Prepare source maps")
  }

  import autoImport._

  override def trigger = allRequirements

  override def requires = ScalaJSPlugin &&  JvmPlugin

  override lazy val projectSettings = Seq(
    dev := devTask.value
  )
  lazy val devTask =
    Def.task {
      val js = {
        fastOptJS in Compile
      }.value

      val jsOutputDirectory = js.data.getParent

      // Prepare the directory for local scala.js source files
      val sourcesDirName = "scala-js-sources"
      val sourcesDirectory = Paths.get(s"$jsOutputDirectory/$sourcesDirName")
      println(s"Preparing scala.js sources at: $sourcesDirectory")
      if (!Files.exists(sourcesDirectory)) Files.createDirectory(sourcesDirectory)

      // Read the generated scala.js sourcemap
      val sourceMapPath = Paths.get(s"${js.data}.map")
      println(s"Loading generated scala.js sourcemap: $sourceMapPath")
      val sourceMap = Source.fromFile(sourceMapPath.toString).mkString

      // Parse it into JSON using the ujson library
      val json = ujson.read(sourceMap)
      val githubUrl = "https://raw.githubusercontent.com/(.*)"
      val filePathRegex = githubUrl.r
      val sources = json("sources").arr.map(_.str)

      // Map all generated sources to the downloaded local files (only if possible)
      val fixedSources = sources.map(url => {
        var fixedUrl = url
        if (url.matches(githubUrl)) {
          val filePathRegex(filePathStr) = url
          try {
            val filePath = Paths.get(s"$sourcesDirectory/$filePathStr")
            if (!Files.exists(filePath)) {
              println(s"Fetching scala.js source file: $filePath")
              Files.createDirectories(filePath.getParent)
              Files.createFile(filePath)
              val contents = Source.fromURL(url).mkString // download scala source
              Files.write(filePath, contents.getBytes)
            }
            fixedUrl = s"./$sourcesDirName/$filePathStr"
          } catch {
            case error: Throwable => println(s"could not process $url\n$error")
          }
        }

        fixedUrl
      })
      // Update the sourcemap with the fixed mappings
      json("sources") = fixedSources
      Files.delete(sourceMapPath)
      Files.createFile(sourceMapPath)
      println(s"Updating scala.js sourcemap: $sourceMapPath")
      Files.write(sourceMapPath, json.render().getBytes)
    }
}
