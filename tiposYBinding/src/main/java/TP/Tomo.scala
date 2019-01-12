package TP

class Tomo(unNombre: String, unPrecioCompra: Int, unPrecioVenta: Int, unVolumen: Int, cantidadDeUsos: Int) extends ItemConsumible(unNombre, unPrecioCompra, unPrecioVenta, unVolumen, cantidadDeUsos) with ItemConsumibleConCantidadDeUsos{
  
}