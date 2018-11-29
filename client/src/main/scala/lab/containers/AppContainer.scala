package lab.containers

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import lab.components.StatelessComponent.{ComponentProps, component}
import org.scalajs.dom.{console, document}
import japgolly.scalajs.react.component.ScalaFn.Component

import scala.scalajs.js.Dictionary
import scala.scalajs.js.annotation._

@JSExportTopLevel("AppContainer")
object AppContainer {
  def main(): Unit = {
    mainComponent().renderIntoDOM(document.getElementById("app"))

  }
  def clickHandler(e: ReactMouseEvent): Callback = {
    console.log("Hey!", e)
    Callback.empty
  }

  def mainComponent() = {

    <.div(
      ^.className := "app",
      ^.onClick ==> clickHandler,
      component(
        ComponentProps("hola")
      ),

    )
  }
}
