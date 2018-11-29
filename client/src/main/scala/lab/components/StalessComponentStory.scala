package lab.components


import lab.lib.reactcosmos.Fixture

import scala.scalajs.js.Dictionary

object StalessComponentStory {
  val fixture = new Fixture(
    name = "Stateless Component",
    component = StatelessComponent.component.toJsComponent.raw ,
    props = Dictionary("content" -> "Hello")
  )

}
