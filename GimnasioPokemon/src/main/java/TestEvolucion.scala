
import org.junit.Assert._
import org.junit.Test
class TestEvolucion {
  
  val experiencia = new Condicion(30000) with Experiencia
  val experiencia2 = new Condicion(100000) with Experiencia
  val especieCharizard = new Especie("Charizard", null, null) with Fuego
  val especieCharmeleon = new Especie("Charmeleon", experiencia2, especieCharizard) with Fuego
  val especieCharmander = new Especie("Charmander", experiencia, especieCharmeleon) with Fuego
  val pepe = new Pokemon("Pepe",30000,40,100,60,40, especieCharmander, null)
  
  @Test
  def EvolucionSegunLaExperiencia(): Unit= {

    // evoluciona si le pido
    val newPepe = pepe.evolucionar()
    assertEquals("Charmeleon", newPepe.especie.getNombre())
    assertEquals(90000, newPepe.getExperiencia())
    
    //evoluciona al subir de exp por levantar pesas
    val charizard = newPepe.evolucionar()
    val charizard2 = charizard.levantarPesas(2)
    assertEquals(270002, charizard2.getExperiencia())
    assertEquals("Charizard", charizard2.especie.getNombre())
  
    // evolucion al subir de exp por nadar
    val experiencia3 = new Condicion(200) with Experiencia
    val especieWartortle = new Especie("Wartortle", null, null) with Agua
    val especieSquirtle = new Especie("Squirtle", experiencia3, especieWartortle) with Agua
    val squirtle = new Pokemon("Squirtle",130,50,100,80,50, especieSquirtle, null)
    assertEquals(130, squirtle.getExperiencia())
    val newSquirtle = squirtle.nadar(5)
  
    assertEquals("Wartortle", newSquirtle.especie.getNombre())
  }
  
  
  
  @Test
  def EvolucionSegunIntercambio():Unit ={
    
    // pierde energia al no poder evolucionar por intercambiarlo
    val intercambio = new Condicion(0) with Intercambio
    val especieMachamp = new Especie("Machamp",null, null) with Lucha
    val especieMachoke = new Especie("Machike",intercambio, especieMachamp) with Lucha
    val machamp = new Pokemon("Machamp",130,50,100,80,50, especieMachamp, null)
    val machamp2 = machamp.intercambio()
    assertEquals(40,machamp2.getEnergia())
    
    // evoluciona al ser intercambiado y tener esa condicion para evolucionar
    val machoke = new Pokemon("Machoke", 50,20,100,20,39, especieMachoke, null)
    val machoke2 = machoke.intercambio()
    assertEquals("Machamp", machoke2.especie.getNombre())
  }
  
  @Test
  def EvolucionarSegunPiedra():Unit ={
    val piedraEvolutiva = new PiedraEvolutiva() with Fuego
    val especieRapidash = new Especie("Rapidash", null, null) with Fuego
    val especiePonita = new Especie("Ponita", piedraEvolutiva, especieRapidash) with Fuego
    val poni = new Pokemon("Poni",150,200,300,260,300, especiePonita, null)
  }
}