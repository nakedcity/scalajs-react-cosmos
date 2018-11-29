package lab.lib.reactcosmos

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("react-cosmos-loader/dom", JSImport.Namespace)
@js.native
object CosmosFacade extends js.Object {
  def mount[T](default: T):js.Promise[Any]= js.native
}
