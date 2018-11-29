package lab.facades

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("react-cosmos-loader/dom", JSImport.Namespace)
@js.native
object Cosmos extends js.Object {
  def mount[T](default: T):(js.Promise[Any])= js.native
}
