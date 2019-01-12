package TP


abstract class ItemApilable (var unNombre: String, var unPrecioCompra :Int, var unPrecioVenta :Int, var unVolumen : Int) extends Item(unNombre,unPrecioCompra,unPrecioVenta,unVolumen){

   
  def volumenMaximoParaUnaPilaDefecto(): Int 
}


class Flecha (nombre: String, precioCompra :Int, precioVenta :Int, volumen : Int) extends ItemApilable(nombre,precioCompra,precioVenta,volumen) with Apilable {
  
 override def volumenMaximoParaUnaPilaDefecto() : Int = 200
}


class Saeta (nombre: String, precioCompra :Int, precioVenta :Int, volumen : Int) extends ItemApilable(nombre,precioCompra,precioVenta,volumen) with Apilable {
  
 override def volumenMaximoParaUnaPilaDefecto() : Int = 250
}


class Pergamino (nombre: String, precioCompra :Int, precioVenta :Int, volumen : Int) extends ItemApilable(nombre,precioCompra,precioVenta,volumen) with Apilable{

   override def volumenMaximoParaUnaPilaDefecto() : Int = 150
   
   def usar(item: Magico) : Unit ={
     item.identificarSiEsMagico()
   }
}


class Llave (nombre: String, precioCompra :Int, precioVenta :Int, volumen : Int) extends ItemApilable(nombre,precioCompra,precioVenta,volumen) with Apilable{
  
   override def volumenMaximoParaUnaPilaDefecto() : Int = 222
}