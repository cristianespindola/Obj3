

package TP

import org.junit.Assert._
import org.junit.Test

class TestItemMagico {
  val cinturon = new Cinturon(2)
  val inv1 = new Inventario[Item](3)
  val inv2 = new Inventario[Comerciable](3)
  val personaje1 = new Personaje(inv1,cinturon)
  val vendedorJuan = new Vendedor(inv2) 
  val pila1 = new Pila[ItemApilable]("noPreguntes",5000)
   
  ////////////PARTE 6//////////////////////
  @Test
  def testItemMagicoConEfectoParaElPersonaje():Unit={
      var cascoMagico = new Casco("casco de las tinieblas",120,122,25,14) with UsableSumaDaño with MagicoEfectoDefensa
      personaje1.recogerItem(cascoMagico)//MagicoEfectoDefensa MagicoEfectoVida
      assertEquals(1, personaje1.cantidadDeItemsEnInventario())
      assertEquals(100, personaje1.salud)
      assertEquals(25, personaje1.danho)
      
      personaje1.equiparItem(cascoMagico)
      
      assertEquals(100, personaje1.salud)
      assertEquals(120, personaje1.defensa)
      assertEquals(45, personaje1.danho)
      
    }
  
  @Test
  def testItemMagicoConEfectoYRaroCambiaElPrecio():Unit ={
    var cascoMagicoRaro = new Casco("casco de las tinieblas Raro",120,122,30,14)with MagicoEfectoDefensa with Raro
    var cascoMagicoMuyRaro = new Casco("casco de las tinieblas Muy Raro",120,122,40,14)with MagicoEfectoDefensa with MuyRaro
    var cascoMagicoUnico = new Casco("casco de las tinieblas Unico",120,122,30,14)with MagicoEfectoDefensa with Unico
    personaje1.recogerItem(cascoMagicoRaro)
    assertEquals(1, personaje1.cantidadDeItemsEnInventario())
    assertEquals(240, cascoMagicoRaro.precioC())
    
    personaje1.recogerItem(cascoMagicoMuyRaro)
    assertEquals(2, personaje1.cantidadDeItemsEnInventario())
    assertEquals(360, cascoMagicoMuyRaro.precioC())
    
    personaje1.recogerItem(cascoMagicoUnico)
    assertEquals(3, personaje1.cantidadDeItemsEnInventario())
    assertEquals(600, cascoMagicoUnico.precioC())
    assertEquals(600, cascoMagicoUnico.precioCompra)
 
  }
  
  
 @Test
 def testIdentificarItemMagico(): Unit ={
   
   var pergamino = new Pergamino("pergamino 1", 40, 20, 15)
   var cascoMagicoRaro = new Casco("casco de las tinieblas Raro",120,122,20,14)with MagicoEfectoDefensa with Raro
   personaje1.recogerItem(cascoMagicoRaro)
   personaje1.recogerItem(pergamino)
   personaje1.identificarItemMagico(cascoMagicoRaro, pergamino)
   assertTrue(cascoMagicoRaro.identificado)
 }
 
 
 @Test
 def testAfectarItem(): Unit ={
   
   var cascoMagicoRaro = new Casco("casco de las tinieblas Raro",120,122,20,14)with MagicoEfectoDefensa with Raro
   personaje1.recogerItem(cascoMagicoRaro)
   assertEquals(40, cascoMagicoRaro.unaDefensa)
   assertEquals(56, cascoMagicoRaro.unVolumen2)
 }
 
 @Test
 def testComprarYVenderItemMagico(): Unit ={
   
   var cascoMagicoRaro = new Casco("casco de las tinieblas Raro",120,122,20,14)with MagicoEfectoDefensa with Raro with Comerciable
   vendedorJuan.i.agregarItem(cascoMagicoRaro)
   personaje1.comprarUnItem(cascoMagicoRaro, vendedorJuan)
   personaje1.oroDisponible += 1000000
   personaje1.comprarUnItem(cascoMagicoRaro, vendedorJuan)
   assertFalse(vendedorJuan.contieneItemEnInventario(cascoMagicoRaro))
   assertTrue(personaje1.contieneItemEnInventario(cascoMagicoRaro))
   assertEquals(999860, personaje1.oroDisponible)
 }
  
}