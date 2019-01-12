

package TP


trait Comerciable extends Item {
   override def utlizarItem(personaje : Personaje) : Unit
   override def sosComerciable() = true
}

trait Usable extends Item {
   override def utlizarItem(personaje : Personaje) : Unit
   override def desUtlizarItem(personaje : Personaje) : Unit
}

trait UsableSumaDefensa extends Usable{
   override def utlizarItem(personaje : Personaje) : Unit = personaje.sumarDefensa(20) 
   override def desUtlizarItem(personaje : Personaje) : Unit = personaje.defensa-=20
   
}

trait UsableSumaDaño extends Usable{
  override def utlizarItem(personaje : Personaje) : Unit = personaje.sumarDanho(20)
  override def desUtlizarItem(personaje : Personaje) : Unit= personaje.danho-=20
}



trait ItemConsumibleConCantidadDeUsos extends ItemConsumible { 
   
  override def utlizarItem(personaje : Personaje) : Unit ={ 
    super.setearUsosDeItem  // Se disminuye en 1 la cantidad de usos del item consumible.
    super.utlizarItem(personaje)
    if(this.cantidadDeUsos == 0){
       personaje.tirarItem(this)
       // Si se acaba la cantidad de usos del item consumible, se lo quita del inventario del personaje.
    }
   }
  }


trait Consumible extends Usable{
  // NO FUNCIONA BIEN, VER EN CLASE. O PRUEBEN USTEDES SI PUEDEN HACER QUE FUNCIONE
  def cantUsos:Int 
  var usos:Int = cantUsos
   override def utlizarItem(personaje : Personaje) : Unit = 
      if(usos>0){super.utlizarItem(personaje);usos-=1  } else {
        personaje.tirarItem(this)// falta poner si la cantadeUsos des 0 que el item 
      }
                                                                  // se elimine del invetario
}

trait ConsumibleUsos5 extends Consumible{
  override def cantUsos = 5
}

trait Posiones extends Consumible{
  val cantUsos:Int = 1
}

trait Reglas extends ItemEquipable{
  abstract override def propriedadRequerida :String = ""
  abstract override def cantidadDePuntos : Int = 0
 // COMPLETAR
}

trait ReglaDeAgilidad extends Reglas{
  abstract override def propriedadRequerida :String = "Agilidad"
 abstract override def cantidadDePuntos : Int = 30
}

trait ReglaDeFuerza extends Reglas{
  abstract override def propriedadRequerida :String = "Fuerza"
 abstract override def cantidadDePuntos : Int = 50
}

trait Gema extends Item{
  override def utlizarItem(personaje : Personaje) : Unit
  override def sosRuna():Boolean=false
}

trait Ruby extends Gema{
   override def utlizarItem(pers : Personaje) : Unit = pers.salud +=100
   override def desUtlizarItem(pers : Personaje) : Unit = pers.salud-=pers.salud / 2
}

trait Runa extends Item{
  override def utlizarItem(pers : Personaje) : Unit
  override def desUtlizarItem(pers : Personaje) : Unit
  override def sosRuna():Boolean=true
}

trait Ro extends Runa {
  override def utlizarItem(pers : Personaje) : Unit = pers.salud +=100
  override def desUtlizarItem(pers : Personaje) : Unit = pers.salud -= 50
  override def sosRuna():Boolean=true
}
trait Ca extends Runa{
  override def utlizarItem(pers : Personaje) : Unit = pers.sumarDanho(50) 
  override def desUtlizarItem(pers : Personaje) : Unit = pers.danho -= 25
  override def sosRuna():Boolean=true
}

trait Apilable extends Usable{
  override def utlizarItem(personaje : Personaje) : Unit
  override def desUtlizarItem(personaje : Personaje) : Unit
  
}

trait Magico extends ItemEquipable{
  
  super.afectarItem()
  override def utlizarItem(personaje: Personaje) : Unit ={
  super.utlizarItem(personaje)
  }
  
  override def desUtlizarItem(personaje: Personaje) : Unit ={
    super.desUtlizarItem(personaje)
  }
}


trait MagicoEfectoDaño extends Magico{
  override def utlizarItem(personaje : Personaje) : Unit ={
    personaje.sumarDanho(20)
    super.utlizarItem(personaje)
  }
  override def desUtlizarItem(personaje : Personaje) : Unit ={
    personaje.danho-=20
    super.desUtlizarItem(personaje) 
  }
 }

trait MagicoEfectoVida extends Magico{
  override def utlizarItem(personaje : Personaje) : Unit ={
    personaje.sumarVida(100)
    super.utlizarItem(personaje)
  }
  override def desUtlizarItem(personaje : Personaje) : Unit = {
    super.desUtlizarItem(personaje)
    personaje.salud-=100
  }
}


trait MagicoEfectoDefensa extends Magico{
  override def utlizarItem(personaje : Personaje) : Unit ={
    super.utlizarItem(personaje)
    personaje.sumarDefensa(20)
  }
  override def desUtlizarItem(personaje : Personaje) : Unit ={
    personaje.defensa-=20  
    super.desUtlizarItem(personaje)
  }
}



trait Raro extends Magico{
  override def precioC():Int = super.precioC()*2
  override def precioV():Int = super.precioV()*2
  super.ponerPrecioCompra(this.precioC())
  super.ponerPrecioVenta(this.precioV())
  }


trait MuyRaro extends Magico{
  override def precioC():Int = super.precioC()*3
  override def precioV():Int = super.precioV()*3
  super.ponerPrecioCompra(this.precioC())
  super.ponerPrecioVenta(this.precioV())  
}

trait Unico extends Magico{
  override def precioC():Int = super.precioC()*5
  override def precioV():Int = super.precioV()*5
  super.ponerPrecioCompra(this.precioC())
  super.ponerPrecioVenta(this.precioV())  
}

