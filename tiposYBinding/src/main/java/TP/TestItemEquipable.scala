
package TP

import org.junit.Assert._
import org.junit.Test

class TestItemEquipable {
  val cinturon = new Cinturon(2)
  val inv1 = new Inventario[Item](3)
  val inv2 = new Inventario[Comerciable](3)
  val personaje1 = new Personaje(inv1,cinturon)
  val cinturon2 = new Cinturon(2)
  val inv3 = new Inventario[Item](3)
  val personaje2 = new Personaje(inv3,cinturon2)
  val vendedorJuan = new Vendedor(inv2)
  
  /////////////// PARTE 4/////////////////////////////
  @Test
  def EquiparUnItemEquipableAUnPersonaje(): Unit = {
    var item1 = new Item("espada Loca",50,30,40)with Comerciable
    var item2= new Item ("espada trucha",25,32,40) 
    var item3= new Item("casco Loco",50,30,40)with Comerciable
    var cascoDeCuero = new Casco("casco de cuero",50,100,50,40)with UsableSumaDefensa
    personaje1.recogerItem(item1)
    personaje1.recogerItem(cascoDeCuero)
    assertEquals(2,personaje1.cantidadDeItemsEnInventario())
    assertEquals(null,personaje1.casco)
    assertEquals(100,personaje1.defensa)
    
    personaje1.equiparItem(cascoDeCuero)
    assertEquals(1,personaje1.cantidadDeItemsEnInventario())
    assertEquals(120,personaje1.defensa)
    assertEquals(cascoDeCuero,personaje1.casco)
  }
    
  @Test
  def EquiparUnItemEquipableConReglaDeAgilidadAUnPersonaje(): Unit = {
    var item1 = new Item("espada Loca",50,30,40)with Comerciable
    var item2= new Item ("espada trucha",25,32,40) 
    var item3= new Item("casco Loco",50,30,40)with Comerciable
    var cascoDeCuero = new Casco("casco de cuero",50,100,40,40)with UsableSumaDefensa with ReglaDeAgilidad
    personaje1.recogerItem(item1)
    personaje1.recogerItem(cascoDeCuero)
    assertEquals(2,personaje1.cantidadDeItemsEnInventario())
    assertEquals(null,personaje1.casco)
    assertEquals(100,personaje1.defensa)
    
    personaje1.equiparItem(cascoDeCuero)
    assertEquals(1,personaje1.cantidadDeItemsEnInventario())
    assertEquals(120,personaje1.defensa)
    assertEquals(cascoDeCuero,personaje1.casco)
  }  
  
  @Test
  def DesEquiparUnItemEquipableAUnPersonaje(): Unit = {
    var item1 = new Item("espada Loca",50,30,40)with Comerciable
    var item2= new Item ("espada trucha",25,32,40) 
    var item3= new Item("casco Loco",50,30,40)with Comerciable
    var cascoDeCuero = new Casco("casco de cuero",50,100,35,40)with UsableSumaDefensa
    personaje1.recogerItem(item1)
    personaje1.recogerItem(cascoDeCuero)
    assertEquals(2,personaje1.cantidadDeItemsEnInventario())
    assertEquals(null,personaje1.casco)
    assertEquals(100,personaje1.defensa)
    
    personaje1.equiparItem(cascoDeCuero)
    assertEquals(1,personaje1.cantidadDeItemsEnInventario())
    assertEquals(120,personaje1.defensa)
    assertEquals(cascoDeCuero,personaje1.casco)
    
    personaje1.desequiparItem(cascoDeCuero)
    assertEquals(2,personaje1.cantidadDeItemsEnInventario())
    assertEquals(100,personaje1.defensa)
    assertEquals(null,personaje1.casco)
  }
  
    @Test
  def TirarUnItemEquipadoConReglaDeAgilidadAUnPersonaje(): Unit = {
    var item1 = new Item("espada Loca",50,30,40)with Comerciable
    var item2= new Item ("espada trucha",25,32,40) 
    var item3= new Item("casco Loco",50,30,40)with Comerciable
    var cascoDeCuero = new Casco("casco de cuero",50,100,35,40)with UsableSumaDefensa with ReglaDeAgilidad
    personaje1.recogerItem(item1)
    personaje1.recogerItem(cascoDeCuero)
    assertEquals(2,personaje1.cantidadDeItemsEnInventario())
    assertEquals(null,personaje1.casco)
    assertEquals(100,personaje1.defensa)
    
    personaje1.equiparItem(cascoDeCuero)
    assertEquals(1,personaje1.cantidadDeItemsEnInventario())
    assertEquals(120,personaje1.defensa)
    assertEquals(cascoDeCuero,personaje1.casco)
    
    personaje1.tirarItemEquipado(cascoDeCuero)
    
    assertEquals(100,personaje1.defensa)// LA DEFENSA BAJA
    assertEquals(null,personaje1.casco) // EL SLOT DE CASCO QUEDA VACIO
}
    @Test
  def EquiparUnItemConReglaDeFuerzaQueElPersonajeNoPuedeSatisfacerEsaRegla(): Unit = {
    var item1 = new Item("espada Loca",50,30,40)with Comerciable
    var item2= new Item ("espada trucha",25,32,40) 
    var item3= new Item("casco Loco",50,30,40)with Comerciable
    var cascoDeCuero = new Casco("Casco de cuero Florenciano",50,100,40,40)with UsableSumaDefensa with ReglaDeFuerza
    var cascoDeCueroDeValesco = new Casco("Casco de cuero valesciano",64,1000,50,45)with UsableSumaDefensa with ReglaDeFuerza
    personaje1.recogerItem(item1)
    personaje1.recogerItem(cascoDeCuero)
    assertEquals(2,personaje1.cantidadDeItemsEnInventario())
    assertEquals(null,personaje1.casco)
    assertEquals(100,personaje1.defensa)
    
    personaje1.equiparItem(cascoDeCuero) 
    assertEquals(2,personaje1.cantidadDeItemsEnInventario())
    assertTrue(personaje1.contieneItemEnInventario(item1))
    assertEquals(100,personaje1.defensa)
    assertEquals(null,personaje1.casco) // PODEMOS ver como no se pudo equipar el casco ya que pide 50 de fuerza y el 
                                            // tiene solo 30
    
    personaje2.fuerza= 60
    personaje2.recogerItem(cascoDeCueroDeValesco)
    assertEquals(1,personaje2.cantidadDeItemsEnInventario())
    assertEquals(null,personaje2.casco)
    assertEquals(100,personaje2.defensa)
    personaje2.equiparItem(cascoDeCueroDeValesco)
    
    assertEquals(0,personaje2.cantidadDeItemsEnInventario()) // ACA PODEMOS OBSERVAR QUE EL Personaje2 cumple con los requisitos
    assertEquals(cascoDeCueroDeValesco,personaje2.casco) // Entonces deja colocarse el casco 
    assertEquals(120,personaje2.defensa)       
  }  
    
}