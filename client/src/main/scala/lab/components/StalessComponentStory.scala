package lab.components


import lab.lib.reactcosmos.Fixture
import lab.components.StatelessComponent.ComponentProps

object StalessComponentStory {
  val fixture = new Fixture(
    name = "Stateless Component",
    component = StatelessComponent.component.toJsComponent.raw,
    props = ComponentProps(content = "hello 2")
  )

}
