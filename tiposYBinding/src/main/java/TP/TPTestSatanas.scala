

package TP
import org.junit.Assert._
import org.junit.Test

class TestSatanas {
  val cinturon = new Cinturon(2)
  val inv1 = new Inventario[Item](3)
  val inv2 = new Inventario[Comerciable](3)
  val personaje1 = new Personaje(inv1,cinturon)
  val vendedorJuan = new Vendedor(inv2)
  val pila1 = new Pila[ItemApilable]("pila de lo que venga",500) with Comerciable
  val pila3 = new Pila[Flecha] ("pila de flechas", 400) with Comerciable
  
  
@Test
  def unPersonajeRecojeVariosItemsHastaNoTenerCapacidad(): Unit = {
    var item1 = new Item("espada Loca",0,0,40)
    var item2= new Item ("espada trucha",0,0,40) 
    var item3= new Item("casco Loco",0,0,40)
    personaje1.recogerItem(item1)
    assertEquals(1,personaje1.cantidadDeItemsEnInventario())
    assertEquals(1,item1.id)
     
    personaje1.recogerItem(item2)
    assertEquals(2,personaje1.cantidadDeItemsEnInventario())
    assertEquals(2,item2.id)
     
    personaje1.recogerItem(item3)
    assertEquals(3,item3.id)
    assertEquals(3,personaje1.cantidadDeItemsEnInventario())
    
    personaje1.recogerItem(item3)
    //Al no haber espacio el item no se agrega al inventario
    assertNotEquals(4,personaje1.cantidadDeItemsEnInventario())

  }  
  
  @Test
  def unPersonajeTiraUnItemDeSuIventario(): Unit = {
    var item1 = new Item("espada Loca",0,0,40)
    var item2= new Item ("espada trucha",0,0,40) 
    var item3= new Item("casco Loco",0,0,40)
    personaje1.recogerItem(item1)
    assertEquals(1,personaje1.cantidadDeItemsEnInventario())
    personaje1.recogerItem(item2)
    assertEquals(2,personaje1.cantidadDeItemsEnInventario())
    
    personaje1.tirarItem(item1)
    assertEquals(1,personaje1.cantidadDeItemsEnInventario())
    // se puede ver que al eliminar el item1 solo queda el item2 en el iventario
    assertFalse(personaje1.contieneItemEnInventario(item1))
    assertTrue(personaje1.contieneItemEnInventario(item2))

  } 
  
    @Test
    def unPersonajeUsaUnItemDeSuInventario(): Unit = {
      
    var item1 = new ItemConsumible("espada",0,0,40,2) with UsableSumaDefensa with ItemConsumibleConCantidadDeUsos
    var item2 = new Item("flecha", 0, 0, 30) with UsableSumaDefensa
    var item3 = new Item ("espada trucha",0,0,40) with UsableSumaDaño
    var item4 = new Item("casco Loco",0,0,40)with UsableSumaDefensa
    var item5 = new Tomo("tomo 1", 0,0,15,5) 
    var item6 = new OjoDeAnok("ojo", 0,0,5)  

    personaje1.recogerItem(item1)
    assertEquals(1,personaje1.cantidadDeItemsEnInventario())
    personaje1.recogerItem(item2)
    assertEquals(2,personaje1.cantidadDeItemsEnInventario())
    personaje1.recogerItem(item3)
    assertEquals(3, personaje1.cantidadDeItemsEnInventario())
    
    
    assertEquals(2, item1.cantidadDeUsos)
    assertEquals(100, personaje1.defensa)
    personaje1.usarUnItem(item1)
    assertEquals(120, personaje1.defensa)
    assertEquals(1, item1.cantidadDeUsos)
   
    
    assertEquals(120, personaje1.defensa)
    personaje1.usarUnItem(item2) // AGREGA 20 A LA DEFENSA DEL PERSONAJE
    assertEquals(140, personaje1.defensa)
    
    
    assertEquals(25,personaje1.danho)
    personaje1.usarUnItem(item3) // AGREGA 20 AL DAÑO DEL PERSONAJE
    assertEquals(45, personaje1.danho)
    
    personaje1.recogerItem(item4) 
    // No se puede agregar más items debido a que se llegó a la capacidad máxima del inventario
    assertNotEquals(4, personaje1.cantidadDeItemsEnInventario())
    
    personaje1.usarUnItem(item1)
    assertEquals(0, item1.cantidadDeUsos)
    assertEquals(2, personaje1.cantidadDeItemsEnInventario())
    
    personaje1.recogerItem(item5)
    assertEquals(3, personaje1.cantidadDeItemsEnInventario())
    
    personaje1.usarUnItem(item5)
    assertEquals(4, item5.cantidadDeUsos)
    
    personaje1.tirarItem(item5)
    assertEquals(2, personaje1.cantidadDeItemsEnInventario())
  }
    
  
  @Test
  def moverUnaPosionDelCinturonAlIventarioYViceversa(): Unit = {
    var item1 = new Item("posion Multifrutas",0,0,40)with Posiones
    var item2= new Item ("Pergamino Tel avil",0,0,40) with UsableSumaDaño
    var item3= new Item("posion MultifrutasV2",0,0,40)with UsableSumaDefensa with Posiones 
    personaje1.recogerItem(item1)
    assertEquals(1,personaje1.cantidadDeItemsEnInventario())
    personaje1.recogerItem(item2)
    assertEquals(2,personaje1.cantidadDeItemsEnInventario())
    personaje1.recogerItem(item3)
   
    assertEquals(3,personaje1.cantidadDeItemsEnInventario())
    personaje1.moverPosionACinturon(item1) 
    assertEquals(2,personaje1.cantidadDeItemsEnInventario())
    assertEquals(1,personaje1.cinturon.items.size)// Verifico si el cinturon tiene la posion
    
    personaje1.moverPosionAInventario(item1)
    assertEquals(3,personaje1.cantidadDeItemsEnInventario())// Verifico si el inventario tiene la posion
    assertEquals(0,personaje1.cinturon.items.size)
     
    

  } 
    
    /////////////PARTE 2 TEST////////////////
  @Test
  def venderUnItemAUnVendedor(): Unit = {
    var item1 = new Item("espada Loca",50,30,40)with Comerciable
    var item2= new Item ("espada trucha",25,32,40) 
    var item3= new Item("casco Loco",15,21,40)
    personaje1.recogerItem(item1)
    personaje1.recogerItem(item2)
    assertEquals(2,personaje1.cantidadDeItemsEnInventario())
    
    assertEquals(0,vendedorJuan.cantidadDeItemsEnInventario())
    assertEquals(100,personaje1.oroDisponible)
    personaje1.venderUnItem(item1, vendedorJuan)
    assertEquals(1,vendedorJuan.cantidadDeItemsEnInventario())
    assertEquals(130,personaje1.oroDisponible)
    assertEquals(1,personaje1.cantidadDeItemsEnInventario())
    // se puede ver que al vender el item1 solo queda el item2 en el iventario
    assertTrue(personaje1.contieneItemEnInventario(item2))
    assertTrue(vendedorJuan.contieneItemEnInventario(item1))
  }
  
  
  @Test
  def comprarUnItemAUnVendedor(): Unit = {
    var item1 = new Item("espada Loca",50,30,40)with Comerciable
    var item2= new Item ("espada trucha",25,32,40) 
    var item3= new Item("casco Loco",50,30,40)with Comerciable
    personaje1.recogerItem(item1)
    personaje1.recogerItem(item2)
    assertEquals(2,personaje1.cantidadDeItemsEnInventario())
    assertEquals(100,personaje1.oroDisponible)
    
    vendedorJuan.i.agregarItem(item3)
    assertEquals(1,vendedorJuan.cantidadDeItemsEnInventario())
    
    personaje1.comprarUnItem(item3, vendedorJuan)
    assertEquals(50,personaje1.oroDisponible)
    assertEquals(3,personaje1.cantidadDeItemsEnInventario())
    assertEquals(0,vendedorJuan.cantidadDeItemsEnInventario())
    assertTrue(personaje1.contieneItemEnInventario(item3))
  }
  
  
  /////////// PARTE 3 TEST  /////////////
 
 @Test
 def juntarDosItemsApilablesALaPila(): Unit = {
   
   var flecha1 = new Flecha("flecha 1", 10, 20, 10)
   var flecha2 = new Flecha("flecha 2", 20, 25, 20)
   var flecha3 = new Flecha("flecha 3", 10, 15, 15)
   var flecha4 = new Flecha("flecha 4", 15, 20, 20)
   
   var pila2 = new Pila[ItemApilable]("pila pila pila", 300)
   pila1.agregarItemALaPila(flecha1)
   pila1.agregarItemALaPila(flecha2)
   pila2.agregarItemALaPila(flecha3)
   pila2.agregarItemALaPila(flecha4)
   assertEquals(2, pila1.cantidadDeItemsEnLaPila())
   assertEquals(2, pila2.cantidadDeItemsEnLaPila())
   
   var pilaNueva = pila1.juntarDosItemsALaPila(pila1, pila2)
   assertEquals(4, pilaNueva.cantidadDeItemsEnLaPila())
   assertEquals(55, pilaNueva.unPrecioCompra)
   assertEquals(80, pilaNueva.unPrecioVenta)
   assertEquals(65, pilaNueva.unVolumen)
 }
 
 
 @Test
 def agregarUnItemAplilableALaPila(): Unit = {
   var item1 = new Pergamino("Pergamino 1", 0, 0,20)
   var item2 = new Flecha("flecha 1", 0,0,30)
   var item3 = new Flecha("flecha 2", 0, 0,40)
   pila1.agregarItemALaPila(item3)
   assertEquals(1, pila1.cantidadDeItemsEnLaPila())
   pila1.agregarItemALaPila(item2)
   assertEquals(2, pila1.cantidadDeItemsEnLaPila())
   pila1.agregarItemALaPila(item1)
   assertEquals(2, pila1.cantidadDeItemsEnLaPila())
  
 }
 
 
 @Test
 def sacarUnItemAplilableDeLaPila(): Unit = {
   var item1 = new Llave("llave 1", 0, 0,20) 
   var item2 = new Llave("llave 2", 0,0,30) 
   var item3 = new Saeta("saeta 1", 0, 0,40)
   pila1.agregarItemALaPila(item1)
   assertEquals(1, pila1.cantidadDeItemsEnLaPila())
   pila1.agregarItemALaPila(item2)
   assertEquals(2, pila1.cantidadDeItemsEnLaPila())
   pila1.sacarItemDeLaPila(item1)
   assertEquals(1, pila1.cantidadDeItemsEnLaPila())
   // Se eliminó el item1 de la pila
   assertTrue(pila1.contieneItemEnLaPila(item2))
   // Se verifica que haya quedado en la pila el item2
 }
 
 
 @Test
 def usarUnaPila(): Unit = {
   
   var item1 = new Flecha("flecha destructiva", 0, 0, 30) with UsableSumaDefensa
   var item2 = new Flecha("flecha tranca", 0, 0, 45) with UsableSumaDaño 
   var item3 = new Flecha("flecha poderosa", 0, 0, 55) with UsableSumaDaño
   var item4 = new Saeta("saeta 1", 0, 0, 60) 
   
   
   assertEquals(0, personaje1.cantidadDeItemsEnInventario())
   var pila = personaje1.apilarItem(item1)
   assertEquals(1, personaje1.cantidadDeItemsEnInventario())
   assertEquals(1, pila.cantidadDeItemsEnLaPila())
   personaje1.apilarItem(item2)
   assertEquals(2, pila.cantidadDeItemsEnLaPila())
   personaje1.apilarItem(item4)
   assertEquals(2, pila.cantidadDeItemsEnLaPila())
   // Se verifica que se hayan apilado dos items en la pila del personaje
   
   personaje1.desapilarItem(item1, pila)
   assertEquals(1, pila.cantidadDeItemsEnLaPila())
   
   assertEquals(100, personaje1.defensa)
   assertEquals(25, personaje1.danho)
   
   personaje1.usarUnItem(pila)
   assertEquals(45, personaje1.danho)
   assertEquals(0, pila.cantidadDeItemsEnLaPila())   
 }
 
 
 @Test
 def comprarUnaPila(): Unit ={
   
   var item1 = new Pergamino("pergamino 1", 40, 20, 15) with Comerciable
   var item2 = new Pergamino("pergamino 2", 30, 15, 15) with Comerciable
   var item3 = new Pergamino("pergamino 3", 25, 20, 15) with Comerciable
   pila1.agregarItemALaPila(item1)
   pila1.agregarItemALaPila(item2)
   pila1.agregarItemALaPila(item3)
   assertEquals(3, pila1.cantidadDeItemsEnLaPila())
   
   assertEquals(100, personaje1.oroDisponible)
   vendedorJuan.i.agregarItem(pila1)
   assertEquals(1, vendedorJuan.cantidadDeItemsEnInventario())
   
   personaje1.comprarUnaPila(pila1, vendedorJuan)
   assertEquals(5, personaje1.oroDisponible)
   assertEquals(1, personaje1.cantidadDeItemsEnInventario())
   assertEquals(0, vendedorJuan.cantidadDeItemsEnInventario())
   assertTrue(personaje1.contieneItemEnInventario(pila1))
 }
 
 
 @Test
 def venderUnaPila(): Unit ={
   
   var item1 = new Saeta("saeta 1", 50, 50, 20) with Comerciable
   var item2 = new Saeta("saeta 2", 30, 40, 20) with Comerciable
   var item3 = new Saeta("saeta 3", 35, 38, 20) with Comerciable
   pila1.agregarItemALaPila(item1)
   pila1.agregarItemALaPila(item2)
   pila1.agregarItemALaPila(item3)
   assertEquals(3, pila1.cantidadDeItemsEnLaPila())
   
   personaje1.recogerItem(pila1)
   assertEquals(1, personaje1.cantidadDeItemsEnInventario())
   
   assertEquals(100, personaje1.oroDisponible)
   personaje1.venderUnaPila(pila1, vendedorJuan)
   
   assertEquals(228, personaje1.oroDisponible)
   assertEquals(0, personaje1.cantidadDeItemsEnInventario())
   assertEquals(1, vendedorJuan.cantidadDeItemsEnInventario())
   assertTrue(vendedorJuan.contieneItemEnInventario(pila1))
    
 } 
}