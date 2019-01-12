

package TP

class Item(nombreItem :String, var precioCompra :Int, var precioVenta :Int, var volumen: Int){
  var id: Int = 0
    def sosPila()=false
    def satisface(item  :ItemApilable):Boolean = false
    def agregarItemALaPila(item:ItemApilable):Unit = null
    def utlizarItem(personaje : Personaje) : Unit = println("Este item no es Usable")
    def ponerId(i :Int) : Unit = id = i
    def desUtlizarItem(personaje : Personaje) : Unit = println("Este item no es Usable")
    def sosRuna(): Boolean = false

    def efectoExtra(pers:Personaje):Unit = null
    def sosComerciable() = false
    def retirarEfectoExtra(pers:Personaje): Unit = null
    
    def ponerPrecioCompra(x : Int) : Unit = this.precioCompra = x
    
    def ponerPrecioVenta(y : Int) : Unit = this.precioVenta = y

}