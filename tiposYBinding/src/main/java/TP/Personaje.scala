

package TP

class Personaje(var inventario :Inventario[Item], var cinturon :Cinturon,var defensa:Int=100,var danho:Int=25, var oroDisponible:Int=100) {
  var agilidad: Int = 50
  var fuerza: Int = 30
  var salud: Int = 100
  var casco: Casco = null;
  var engarzable: Engarzable = null;
  //var manoIsq : Mano = null;
  //var manoDer : Mano = null;
  var torso : Torso = null;                      //CREAR ESOS ITEM EQUIPABLES COMO CLASES
  //var botas : Bota = null;
  //var amuleto : Amuleto = null; 
  //var anilloManoIsq : Anillo = null; 
  //var anilloManoDer: Anillo = null;
  var guantes: Guante = null;

  def recogerItem(item: Item) : Unit = inventario.agregarItem(item)
  def tirarItem(item: Item) : Unit = inventario.eliminarItem(item)
  def usarUnItem(item: Item): Unit = item.utlizarItem(this)
  def apilarItem(item: ItemApilable): Pila[ItemApilable] = {var pila = this.inventario.hayPilaDisponible(item)
                                                    if(pila.isEmpty){
                                                      var nuevaPila = new Pila[ItemApilable]("pila", item.volumenMaximoParaUnaPilaDefecto()) 
                                                      nuevaPila.agregarItemALaPila(item)
                                                      this.inventario.agregarItem(nuevaPila)
                                                      return nuevaPila
                                                    } 
                                                    else{
                                                     pila.get.agregarItemALaPila(item)
                                                     return null
                                                    }
  }
  def apilarItems(pila: Pila[ItemApilable]): Unit = this.inventario.agregarItem(pila) 
  def desapilarItem(item: ItemApilable, pila: Pila[ItemApilable]): Unit = pila.sacarItemDeLaPila(item)
  def desapilarItems(pila: Pila[ItemApilable]): Unit = this.inventario.eliminarItem(pila)
  def sumarDefensa(i :Int)= defensa +=i 
  def sumarDanho(i :Int)= danho +=i
  def sumarVida(i :Int)= salud +=i
  def comprarUnItem(item: Comerciable , vendedor: Vendedor): Unit =if(item.precioCompra <= oroDisponible){
                                                                      oroDisponible = oroDisponible - item.precioCompra
                                                                      vendedor.i.eliminarItem(item)
                                                                      inventario.agregarItem(item)
                                                                      }else{println("NO HAY DINERO SUFICIENTE")}
  
  def venderUnItem(item: Comerciable , comprador: Vendedor): Unit = {oroDisponible = oroDisponible + item.precioVenta
                                                                     inventario.eliminarItem(item)
                                                                     comprador.i.agregarItem(item)}
  
  
  def comprarUnaPila(pila: Pila[ItemApilable], vendedor: Vendedor): Unit = if(pila.valorDeCompraDeLaPila() <= oroDisponible){
                                                                              oroDisponible = oroDisponible - pila.valorDeCompraDeLaPila()
                                                                              vendedor.i.eliminarPila(pila) 
                                                                              this.apilarItems(pila) 
                                                                              } else{println("NO HAY DINERO SUFICIENTE LOCURA!")}
   
  def venderUnaPila(pila: Pila[ItemApilable] with Comerciable, comprador: Vendedor): Unit = { oroDisponible = oroDisponible + pila.valorDeVentaDeLaPila()
                                                                            this.desapilarItems(pila)
                                                                            comprador.i.agregarItem(pila)
  }
  
  
  // Solo mueve posiones
  def moverPosionAInventario(posion :Posiones) :Unit = {cinturon.eliminarItem(posion)
                                                  recogerItem(posion)}
  // Solo mueve posiones
  def moverPosionACinturon(posion :Posiones) :Unit = {tirarItem(posion);
                                               cinturon.agregarItem(posion)}
  
  def equiparItem(item: ItemEquipable) :Unit = {if(item.puedeEquiparse(this)){
                                                    item.equipar(this)
                                                    if(this.engarzable != null){
                                                      this.engarzable.efectoExtra(this)
                                                    }
                                                    
                                                    }
                                                else{println("NO se pudo equipar Ese item de nombre " + item.unNombre)}}
  
  def desequiparItem(item: ItemEquipable) :Unit = {if(this.engarzable != null){
                                                        this.engarzable.retirarEfectoExtra(this)
                                                        item.desEquipar(this)
                                                    }else{
                                                      item.desEquipar(this)}
                                                   }
  
  def tirarItemEquipado (item:ItemEquipable ):Unit = item.tirarItemEquipado(this)
  
  def engarzar(engarzable:Engarzable, item:Item):Unit ={
    engarzable.enzamblar(item)
  }
  
  def identificarItemMagico (item: Magico, pergamino: Pergamino) : Unit ={
    
    pergamino.usar(item)
  }
  
  def cantidadDeItemsEnInventario() : Int = return this.inventario.cantidadDeItems()
  
  def contieneItemEnInventario(item: Item) : Boolean = return this.inventario.contieneItem(item)

}