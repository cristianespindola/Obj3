

package TP

class Inventario[T<:Item](capacidad :Int,var items:List[T]= Nil) {
  //var items:List[Item]= Nil
  //agrego un item al iventario
  var contadorId : Int = 1
  
  def agregarItem(item: T) : Unit = 
      if(items.size < capacidad)
        {
         items = items.+:(item) 
         item.ponerId(contadorId)
         contadorId += 1
        }
      else{println("NO hay espacio en el inventario")}
  
  def eliminarItem(item: Item) :Unit =
    // filtro la lista por aquel item que quiero eliminar
     items = items.filter( p=> p.id != item.id)
     //items = items.map(p => p.setearIdACadaItem())
  
  def hayPilaDisponible(item:ItemApilable): Option[Item]={
      var pilas = this.items.filter(p=> p.sosPila())
      var pilaElegida = pilas.find(p=> p.satisface(item)) 
      return pilaElegida
  }
  
  def eliminarPila(pila: Pila[ItemApilable]) : Unit =
    items = items.filter(p => p.id != pila.id)
    
  def cantidadDeItems() : Int = return this.items.size  
  
  def contieneItem(item: Item) : Boolean = return this.items.contains(item)
}  