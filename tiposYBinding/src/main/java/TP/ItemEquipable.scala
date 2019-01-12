

package TP

abstract class ItemEquipable(var unNombre: String, var unPrecioCompra :Int, var unPrecioVenta :Int, var unVolumen : Int) extends Item(unNombre,unPrecioCompra,unPrecioVenta,unVolumen)  {
   
 var identificado: Boolean = false
  
 def puedeEquiparse(pers :Personaje) : Boolean 
 
 def equipar(pers :Personaje):Unit
 
 def desEquipar(pers :Personaje):Unit
 
 def identificarSiEsMagico():Unit ={
   this.identificado = true
 }
 
 def tirarItemEquipado(pers :Personaje) : Unit
 
 def precioC():Int = this.unPrecioCompra
 
 def precioV():Int = this.unPrecioVenta
 
 def reglas(pers :Personaje) : Boolean = this.propriedadRequerida match{
                                                   case "" => return true
                                                   case "Agilidad" => pers.agilidad >= this.cantidadDePuntos
                                                   case "Fuerza" => pers.fuerza>= this.cantidadDePuntos
                                               }
 def propriedadRequerida :String = ""
 def cantidadDePuntos : Int = 0
 
 
 def afectarItem() : Unit = null
 
}


class Casco(unNombre: String, unPrecioCompra :Int, unPrecioVenta :Int, var unaDefensa: Int, var unVolumen2 : Int) extends ItemEquipable(unNombre,unPrecioCompra,unPrecioVenta,unVolumen2) {
   override def puedeEquiparse(pers :Personaje) : Boolean = {if(pers.casco == null && this.reglas(pers)){return true} // FALTA IMPLEMENTAR LAS REGLAS DE USO   
                                                               return false} //                    EJ: QUE EL CASCO SOLICITE 150 DE FUERZA
   
   
   // el item a equipar tiene que estar si o si en el inventario del personaje                                                          
   override def equipar(pers :Personaje):Unit = {pers.casco = this
                                                 this.utlizarItem(pers)
                                                 pers.tirarItem(this)}
   // el item a desequipar tiene que estar si o si en algun slot del personaje
   override def desEquipar(pers :Personaje):Unit = {pers.casco = null
                                                 this.desUtlizarItem(pers) // Tiene que dejar ser usado
                                                 pers.recogerItem(this)}
   
   override def tirarItemEquipado(pers :Personaje) : Unit = {pers.casco = null
                                                             this.desUtlizarItem(pers)}
   
   override def afectarItem(): Unit ={
     
     this.unVolumen2 = this.unVolumen2 * 4
     this.unaDefensa = this.unaDefensa + 20
   } 
  } 



class Guante(unNombre: String, unPrecioCompra :Int, unPrecioVenta :Int, var unaDefensa: Int, var unVolumen2 : Int) extends ItemEquipable(unNombre,unPrecioCompra,unPrecioVenta,unVolumen2) {
   override def puedeEquiparse(pers :Personaje) : Boolean = {if(pers.guantes == null && this.reglas(pers)){return true} // FALTA IMPLEMENTAR LAS REGLAS DE USO   
                                                               return false} //                    EJ: QUE EL CASCO SOLICITE 150 DE FUERZA
   
   
   // el item a equipar tiene que estar si o si en el inventario del personaje                                                          
   override def equipar(pers :Personaje):Unit = {pers.guantes = this
                                                 this.utlizarItem(pers)
                                                 pers.tirarItem(this)}
   // el item a desequipar tiene que estar si o si en algun slot del personaje
   override def desEquipar(pers :Personaje):Unit = {pers.guantes = null
                                                 this.desUtlizarItem(pers) // Tiene que dejar ser usado
                                                 pers.recogerItem(this)}
   
   override def tirarItemEquipado(pers :Personaje) : Unit = {pers.guantes = null
                                                             this.desUtlizarItem(pers)}
   
   override def afectarItem(): Unit ={
     
     this.unVolumen2 = this.unVolumen2 * 2
     this.unaDefensa = this.unaDefensa + 20
   }
   
 }



class Torso(unNombre: String, unPrecioCompra :Int, unPrecioVenta :Int, var unaDefensa: Int, var unVolumen2 : Int) extends ItemEquipable(unNombre,unPrecioCompra,unPrecioVenta,unVolumen2) {
   override def puedeEquiparse(pers :Personaje) : Boolean = {if(pers.torso == null && this.reglas(pers)){return true} // FALTA IMPLEMENTAR LAS REGLAS DE USO   
                                                               return false} //                    EJ: QUE EL CASCO SOLICITE 150 DE FUERZA
   
   
   // el item a equipar tiene que estar si o si en el inventario del personaje                                                          
   override def equipar(pers :Personaje):Unit = {pers.torso = this
                                                 this.utlizarItem(pers)
                                                 pers.tirarItem(this)}
   // el item a desequipar tiene que estar si o si en algun slot del personaje
   override def desEquipar(pers :Personaje):Unit = {pers.torso = null
                                                 this.desUtlizarItem(pers) // Tiene que dejar ser usado
                                                 pers.recogerItem(this)}
   
   override def tirarItemEquipado(pers :Personaje) : Unit = {pers.torso = null
                                                             this.desUtlizarItem(pers)}
   
   override def afectarItem(): Unit ={
     
     this.unVolumen2 = this.unVolumen2 * 5
     this.unaDefensa = this.unaDefensa + 20
   }
   
 }
