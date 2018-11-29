package lab.containers

import lab.Fixture
import lab.components.StatelessComponent

import scala.scalajs.js
import scala.scalajs.js.Dictionary
import scala.scalajs.js.special.debugger
import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.dom.console

object AppContainerStory {
  val fixture = new Fixture(
    name = "Stateless Component",
    component = StatelessComponent.component.toJsComponent.raw ,
    props = Dictionary("content" -> "hola")
  )
  console.log(StatelessComponent.component.raw)
  debugger()

  @JSExportTopLevel("default")
  val default = js.Array(fixture)
}



