package lab.components

import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^._
import lab.facades.React.{useEffect, useState}

import scala.scalajs.js
import scala.scalajs.js.Date
import scala.scalajs.js.special.debugger
import scala.scalajs.js.timers.{clearTimeout, setTimeout}
import japgolly.scalajs.react.raw

import scala.scalajs.js.annotation.JSExportTopLevel

object StatelessComponent {

  trait Props extends js.Object {
    val content: String
  }

  implicit class ComponentProps(val content:String) extends Props {

  }

  val component = ScalaFnComponent[Props](props => {
    val (now, updateCounter) = useState(new Date().toTimeString())
    useEffect {
      () => {
        val timer = setTimeout(1000)(
          updateCounter(new Date().toTimeString())
        )
        () => {
          clearTimeout(timer)
        }
      }
    }
    <.div(
      <.div(props.content),
      <.div(now + "hello2")
    )
  })

  def render(props: Props): raw.React.Element = component(props).raw

  js.constructorOf[scalajs.js.Object](component.raw).displayName = "StateLess"
}

