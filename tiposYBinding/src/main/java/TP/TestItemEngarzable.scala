

package TP

import org.junit.Assert._
import org.junit.Test

class TestItemEngarzable {
  val cinturon = new Cinturon(2)
  val inv1 = new Inventario[Item](3)
  val inv2 = new Inventario[Comerciable](3)
  val personaje1 = new Personaje(inv1,cinturon)
  val vendedorJuan = new Vendedor(inv2)
  
  ////////////////////Parte5////////////////////////////////
  @Test
  def DadoUnItemEngarableLeEngarzoUnaGemaRubyEnUnSlotLibre(): Unit = {
      var espada = new Engarzable("espalda magic",100,100, 50) with UsableSumaDefensa
      var ruby = new Item ("Ruby",0,0,0) with  Ruby
      personaje1.recogerItem(ruby)
      personaje1.recogerItem(espada)
      assertEquals(personaje1.cantidadDeItemsEnInventario(), 2)
    
      personaje1.engarzar(espada, ruby) // engarza el ruby a la espada
  
      assertEquals(espada.slots1,ruby)
    }
    
 
    
    @Test
    def DadoUnItemEngarzableLeEngarzaUnRubyEsteSumaLaVidaALPersonajeAlUtilizarlo(): Unit = {
      var espada = new Engarzable("espalda magic",100,100, 50) with UsableSumaDefensa
      var ruby = new Item ("Ruby",0,0,0) with  Ruby
      personaje1.recogerItem(ruby)
      personaje1.recogerItem(espada)
      assertEquals(personaje1.cantidadDeItemsEnInventario(), 2)
    
      personaje1.engarzar(espada, ruby) // engarza el ruby a la espada
      personaje1.equiparItem(espada) // utiliza la espada engarzada y aumenta su salud
    
      assertEquals(personaje1.engarzable,espada)
      assertEquals(personaje1.engarzable.slots1,ruby)
      assertEquals(personaje1.salud,200)
    }
  
    @Test
    def DadoUnItemEngarzableConUnRubyEsteSumaLaVidaALPersonajeAlEquiparloYAlDesequiparloLeQuitaLamitadDeSuSalud(): Unit = {
      var espada = new Engarzable("espalda magic",100,100, 50) with UsableSumaDefensa
      var ruby = new Item ("Ruby",0,0,0) with Ruby with Comerciable
      personaje1.recogerItem(ruby)
      personaje1.recogerItem(espada)
      assertEquals(personaje1.cantidadDeItemsEnInventario(), 2)
    
      personaje1.engarzar(espada, ruby) // engarza el ruby a la espada
      personaje1.equiparItem(espada) // utiliza la espada engarzada y aumenta su salud
    
      assertEquals(personaje1.salud, 200)
    
      personaje1.desequiparItem(espada) // desequipa la espada y baja la salud
      assertEquals(personaje1.salud, 100)
    }
    
    @Test
    def DadoUnPersonajeConUnItemEngarzableYUnaRunaLoUsaaYAlTirarlosNoTieneNadaEnELInventario():Unit={
      var espada = new Engarzable("espalda magic",100,100, 50) with Usable
      var ruby = new Item ("Ruby",0,0,0) with Ruby with Comerciable
      personaje1.recogerItem(ruby)
      personaje1.recogerItem(espada)
      assertEquals(personaje1.cantidadDeItemsEnInventario(), 2)
    
      personaje1.engarzar(espada, ruby) // engarza el ruby a la espada
      personaje1.equiparItem(espada) // utiliza la espada engarzada y aumenta su salud
    
      assertEquals(personaje1.salud, 200)
      
      personaje1.tirarItemEquipado(espada)// tira la espada
      personaje1.tirarItem(ruby) // tira el ruby
      assertEquals(personaje1.salud, 100)
      assertEquals(personaje1.cantidadDeItemsEnInventario(), 0)
    }
    
   @Test
    def DaduUnItemEngarzableLeEngarzoDosRunasEnLosSlotLibres(): Unit ={
      var espada = new Engarzable("espalda magic",100,100, 50) with Usable
      var ro = new Item ("RunaRo",0,0,0) with  Ro
      var ca = new Item ("RunaCa",0,0,0) with Ca
      personaje1.recogerItem(ro)
      personaje1.recogerItem(ca)
      personaje1.recogerItem(espada)
      assertEquals(personaje1.cantidadDeItemsEnInventario(), 3)
    
      personaje1.engarzar(espada, ro) // engarza la runa "ro" en un slot de la espada
      personaje1.engarzar(espada, ca) // engarza la runa "ca" en un slot de la espada
      
      assertEquals(espada.slots1,ro)
      assertEquals(espada.slots2,ca)
    }
    
    @Test
    def DadoUnItemEngarzableConDosRunasAumentaVidaDañoYDefensaDelPersonaje(): Unit = {
      var espada = new Engarzable("espalda magic",100,100, 50) with Usable
      var ro = new Item("Ro",0,0,0) with Ro 
      var ca = new Item("ca",0,0,0) with Ca 
      personaje1.recogerItem(espada)
      personaje1.recogerItem(ca)
      personaje1.recogerItem(ro)

      assertEquals(personaje1.cantidadDeItemsEnInventario(), 3)
      
      personaje1.engarzar(espada, ro)
      personaje1.engarzar(espada,ca)
      
      assertEquals(personaje1.salud, 100)
      
      personaje1.equiparItem(espada) // utiliza la espada con las runas
      
      assertEquals(personaje1.engarzable, espada)
      assertEquals(personaje1.salud, 200)
      assertEquals(personaje1.danho, 75)
      assertEquals(personaje1.defensa, 150)
    }
    
    @Test
    def DaduUnItenEngarzableConDosRunasAlUtilizarloYAumentarSuHabilidadCuandoLoDesequipaBajaLashabilidadesAdquiridas(): Unit ={
      var espada = new Engarzable("espalda magic",100,100, 50) with Usable
      var ro = new Item("Ro",0,0,0) with Ro 
      var ca = new Item("ca",0,0,0) with Ca 
      personaje1.recogerItem(espada)
      personaje1.recogerItem(ca)
      personaje1.recogerItem(ro)
    
      assertEquals(personaje1.cantidadDeItemsEnInventario(), 3)
      
      personaje1.engarzar(espada, ro)
      personaje1.engarzar(espada,ca)
      
      assertEquals(personaje1.salud, 100)
      
      personaje1.equiparItem(espada)
      
      assertEquals(personaje1.engarzable, espada)
      assertEquals(personaje1.salud, 200)
      assertEquals(personaje1.danho, 75)
      assertEquals(personaje1.defensa, 150) 
      
      personaje1.desequiparItem(espada) // deja de utilizar la espada
      assertEquals(personaje1.salud, 150)
      assertEquals(personaje1.danho, 50)
      assertEquals(personaje1.defensa, 100) 
    }
    
    @Test
    def DadoUnPersonajeConDosRunasYUnItenEngarzableAlTirarlosNoTieneNada():Unit={
      var espada = new Engarzable("espalda magic",100,100, 50) with Usable
      var ro = new Item("Ro",0,0,0) with Ro 
      var ca = new Item("ca",0,0,0) with Ca 
      personaje1.recogerItem(espada)
      personaje1.recogerItem(ca)
      personaje1.recogerItem(ro)
    
      assertEquals(personaje1.cantidadDeItemsEnInventario(), 3)
      
      personaje1.engarzar(espada, ro)
      personaje1.engarzar(espada,ca)
      
      assertEquals(personaje1.salud, 100)
      
      personaje1.equiparItem(espada)
      
      assertEquals(personaje1.engarzable, espada)
      assertEquals(personaje1.salud, 200)
      assertEquals(personaje1.danho, 75)
      assertEquals(personaje1.defensa, 150) 
      
      personaje1.tirarItemEquipado(espada) // tira la espada
      personaje1.tirarItem(ca)
      personaje1.tirarItem(ro)
      assertEquals(personaje1.salud, 150)
      assertEquals(personaje1.danho, 50)
      assertEquals(personaje1.defensa, 100) 
      assertEquals(personaje1.cantidadDeItemsEnInventario(), 0)
      
    }
}