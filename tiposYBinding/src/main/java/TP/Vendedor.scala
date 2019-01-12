

package TP

class Vendedor(var i :Inventario[Comerciable]) {  
  
  def cantidadDeItemsEnInventario (): Int = return this.i.cantidadDeItems()
  
  def contieneItemEnInventario (item: Item) : Boolean = return this.i.contieneItem(item)
}