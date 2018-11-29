package lab.lib.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport


object ReactFacade extends Object {

  @JSImport("react", JSImport.Namespace)
  @js.native
  private object ReactJS extends js.Object {
    def useState[T](default: T): js.Tuple2[T, js.Function1[T, Unit]] = js.native

    def useEffect(default: js.Function0[Unit]): Unit = js.native
  }

  def useState[T](default: T): (T, js.Function1[T, Unit]) = {
    val call = ReactJS.useState(default)
    (call._1, call._2)
  }

  def useEffect(default: () => Unit): Unit = {
    ReactJS.useEffect(default)
  }
}
