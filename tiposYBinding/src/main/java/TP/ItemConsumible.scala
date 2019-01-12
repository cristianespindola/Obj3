package TP


abstract class ItemConsumible(var unNombre: String, var unPrecioCompra :Int, var unPrecioVenta :Int, var unVolumen : Int, var cantidadDeUsos: Int) extends Item(unNombre :String, unPrecioCompra :Int, unPrecioVenta :Int, unVolumen: Int) with Usable{
  
  def setearUsosDeItem: Unit ={
    this.cantidadDeUsos = this.cantidadDeUsos - 1
    }
}
 