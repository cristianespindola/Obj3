package TP

class Engarzable (var unnNombre: String, var unnPrecioCompra :Int, var unnPrecioVenta :Int, var unnVolumen : Int) extends ItemEquipable(unnNombre,unnPrecioCompra,unnPrecioVenta,unnVolumen){
    var slots1:Item = null
    var slots2:Item = null
    var slots3:Item = null 
  
  override def puedeEquiparse(pers :Personaje) : Boolean = {if(pers.casco == null && this.reglas(pers)){return true}    
                                                               return false}                     
   
                                                             
   override def equipar(pers :Personaje):Unit = {pers.engarzable = this
                                                 this.utilizar(pers)
                                                 pers.tirarItem(this)}
   
   override def desEquipar(pers :Personaje):Unit = {pers.engarzable = null
                                                 this.desUtlizar(pers) // Tiene que dejar ser usado
                                                 pers.recogerItem(this)}
   
   override def tirarItemEquipado(pers :Personaje) : Unit = {this.desUtlizar(pers)
                                                             pers.engarzable = null
                                                             if(this.hayRunas()){
                                                               this.retirarEfectoExtra(pers)}
                                                             }
   
   
   
  def enzamblar(item:Item){
    this.ingresar(item)
  }
  
  def hayRunas(): Boolean ={
    var runas = this.hayRuna(this.slots1) + this.hayRuna(this.slots2) + this.hayRuna(this.slots3) 
    return ( runas >= 2)
  }
  
  def hayRuna(item:Item):Int ={
    var result = 0
    if(this.slots1.sosRuna()){
      result = 1
    }
    return result
  }
  
  def ingresar(item:Item): Unit={
    if(this.slots1 == null){
      this.slots1 = item
    }
    else{
      if(this.slots2 == null){
      this.slots2 = item
      }
      else{
        if(this.slots3 == null){
          this.slots3 = item
          }
        else{
          println("NO se pudo engarzar Ese item")
        }
      }
    }  
  }
  
  def utilizar(pers:Personaje): Unit={
    if(this.slots1 != null){
      slots1.utlizarItem(pers)
    }
    if(this.slots2 != null){
      slots2.utlizarItem(pers)
    }
    if(this.slots3 != null){
          slots3.utlizarItem(pers)
    }
  }
  
  def desUtlizar(pers:Personaje): Unit={
    if(this.slots1 != null){
      slots1.desUtlizarItem(pers)
    }
    if(this.slots2 != null){
      slots2.desUtlizarItem(pers)
      }
    if(this.slots3 != null){
          slots3.desUtlizarItem(pers)
      }
  }
  
  override def efectoExtra(pers:Personaje): Unit ={
    if(this.hayRunas()){
     pers.sumarDefensa(50) 
    }
  }
  
   override def retirarEfectoExtra(pers:Personaje): Unit ={
    if(this.hayRunas()){
      pers.sumarDefensa(-50)
    }
  }
   
  override def afectarItem(): Unit ={
    
   } 
}