package TP

class Pila[T <: ItemApilable] (var unNombreItem :String, var volumenMaximo: Int, var unPrecioCompra :Int =0, var unPrecioVenta :Int=0, var unVolumen: Int = 0, var items:List[ItemApilable] = Nil) extends Item (unNombreItem, unPrecioCompra, unPrecioVenta, unVolumen){
  
   
  override def sosPila()=true
  
  def tamanioPila(): Int = items.size
  
  protected def cumpleCondicionDeVolumen(item: ItemApilable): Boolean ={
    return (((this.sumaDeLosVolumenesDeLosItems(items) + item.volumen) / 2) < volumenMaximo)
  }
  
  
  def sumaDeLosVolumenesDeLosItems(lista: List[Item]): Int = {
    
     if(lista.isEmpty) 0 else
       lista.head.volumen + this.sumaDeLosVolumenesDeLosItems(lista.tail)
  }
  
  
  override def satisface(item :ItemApilable):Boolean = (this.cumpleCondicionDeVolumen(item))
  
  
  def juntarDosItemsALaPila(pila1: Pila[ItemApilable], pila2: Pila[ItemApilable]) : Pila[ItemApilable] = {
    var pilaNueva = new Pila[ItemApilable]("",0)   
    pilaNueva.unNombreItem = pila1.unNombreItem
    pilaNueva.volumenMaximo = pila1.volumenMaximo + pila2.volumenMaximo
    pilaNueva.unPrecioCompra = pila1.unPrecioCompra + pila2.unPrecioCompra
    pilaNueva.unPrecioVenta = pila1.unPrecioVenta + pila2.unPrecioVenta
    pilaNueva.unVolumen = pila1.unVolumen + pila2.unVolumen
    pilaNueva.items = pila1.items ::: pila2.items
    return pilaNueva
         
   }
  
  override def agregarItemALaPila(item:ItemApilable): Unit ={
         
    if(this.satisface(item)){
     
      items = items.+:(item)
      // Se actualizan los datos de precio de compra, venta y demás de la pila.
      this.unPrecioCompra = this.unPrecioCompra + item.precioCompra
      this.unPrecioVenta = this.unPrecioVenta + item.precioVenta
      this.unVolumen = this.unVolumen + item.volumen
      item.ponerId(items.size) 
    }
    else{
      println("No hay espacio en la pila")
    }   
  }
  

  def sacarItemDeLaPila(item:ItemApilable): Unit = {
    // filtro la pila hasta eliminar el item pasado como parámetro
    items = items.filter( p => p.id != item.id)
    // Se actualizan los datos de precio de compra, venta y demás de la pila.
    this.unPrecioCompra = this.unPrecioCompra - item.precioCompra
    this.unPrecioVenta = this.unPrecioVenta - item.precioVenta
    this.unVolumen = this.unVolumen - item.volumen
  }   
    
  
  override def utlizarItem(personaje : Personaje) : Unit ={
    // PREC: la pila no está vacia 
    
    var item = this.items.head
    item.utlizarItem(personaje)
    this.sacarItemDeLaPila(item)
    // Una vez utilizado el item, se saca de la pila.
  }
  
  
  def sumaDeLosValoresDeCompraDeItems(lista: List[Item]): Int ={
    if(lista.isEmpty) 0 else
       lista.head.precioCompra + this.sumaDeLosValoresDeCompraDeItems(lista.tail)
  }
  
  def sumaDeLosValoresDeVentaDeItems(lista: List[Item]): Int ={
    if(lista.isEmpty) 0 else
       lista.head.precioVenta + this.sumaDeLosValoresDeVentaDeItems(lista.tail)
  }
  
  def valorDeCompraDeLaPila() : Int ={
    
    var resultado = 0
    var itemsComerciables = items.filter( p => p.sosComerciable())
    if(itemsComerciables.size == this.items.size){
      resultado = this.sumaDeLosValoresDeCompraDeItems(itemsComerciables)
      return resultado
    }
    else{
      return resultado
    }
  }
  
  def valorDeVentaDeLaPila(): Int ={
    
    var resultado = 0
    var itemsComerciables = items.filter( p => p.sosComerciable())
    if(itemsComerciables.size == this.items.size){
      resultado = this.sumaDeLosValoresDeVentaDeItems(itemsComerciables)
      return resultado
    }
    else{
      return resultado
    }    
  }
  
  def cantidadDeItemsEnLaPila() : Int = return this.items.size
  
  def contieneItemEnLaPila(item: Item) : Boolean = return this.items.contains(item)
}