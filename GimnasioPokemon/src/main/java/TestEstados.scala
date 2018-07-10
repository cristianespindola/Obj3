
import org.junit.Assert._
import org.junit.Test
class TestEstados {
 
  val especieCharmander = new Especie("Charmander", null, null) with Fuego
  val pepe = new Pokemon("Pepe",30,40,100,60,40, especieCharmander, null)
  
  @Test
  def NadarPOrDiferentesEstadosYTiposDePokemon(): Unit ={
    
    // nadar con tipo Fuego
    val newPepe = pepe.nadar(10)
    assertEquals("KO", newPepe.estado.getNombre())
    
    // nadar con tipo segundario Tierra
    val especieMachamp = new Especie("Machamp", null, null) with Lucha with Tierra
    val machamp = new Pokemon("Machamp",130,50,100,80,40, especieMachamp, null)
    val newMachamp = machamp.nadar(5)
    assertEquals("KO", newMachamp.estado.getNombre())
    
    //nadar con tipo primario Tierra
    val especieMachoke = new Especie("Machoke", null, null) with Tierra with Lucha
    val machoke = new Pokemon("Machamp",130,50,100,80,40, especieMachoke, null)
    val newMachoke = machoke.nadar(5)
    assertEquals("KO", newMachoke.estado.getNombre())
    
    //nadar con estado dormido
    val especieSquirtle = new Especie("Squirtle", null, null) with Agua
    val squirtle = new Pokemon("Squirtle",130,50,100,80,50, especieSquirtle, new Dormido())
    val newSquirtle = squirtle.nadar(5)
    assertEquals("Dormido",newSquirtle.estado.getNombre())
    assertEquals(50, newSquirtle.getEnergia())
    
    // nadar estado paralizado
    val especieWartortle = new Especie("Wartortle", null, null) with Agua
    val wartortle = new Pokemon("Wartortle",130,50,100,80,50, especieWartortle, new Paralizado())
    val newWartortle = wartortle.nadar(5)
    assertEquals("Paralizado",newWartortle.estado.getNombre())
    assertEquals(50, newSquirtle.getEnergia())
    
    // nadar estado KO
    val especieBlastoise = new Especie("Blastoise", null, null) with Agua
    val blastoise = new Pokemon("Blastoise",130,50,100,80,50, especieBlastoise, new KO())
    val newBlastoise = blastoise.nadar(5)
    assertEquals("KO",newBlastoise.estado.getNombre())
    assertEquals(50, newSquirtle.getEnergia())
    
    
  }  
  
  @Test
  def LevantarPesasPorDiferentesEstadosPokemon(): Unit ={
    
    // levantar pesas de mas en estado normal
    val especieMachamp = new Especie("Machamp", null, null) with Lucha
    val machamp = new Pokemon("Machamp",130,50,100,80,40, especieMachamp, null)
    assertEquals(130, machamp.getExperiencia())
    val newMachamp = machamp.levantarPesas(801)
    assertEquals(130,newMachamp.getExperiencia())
    assertEquals(40, newMachamp.getEnergia())
    assertEquals("Paralizado", newMachamp.estado.getNombre())
    
    // levantar peas de mas en estado paralizado
    val newMachamp2 = newMachamp.levantarPesas(801)
    assertEquals(130,newMachamp2.getExperiencia())
    assertEquals(30, newMachamp2.getEnergia())
    assertEquals("KO", newMachamp2.estado.getNombre())
    
    // levantar pesas en estado dormido
    val especieSquirtle = new Especie("Squirtle", null, null) with Agua
    val squirtle = new Pokemon("Squirtle",130,50,100,80,50, especieSquirtle, new Dormido())
    val newSquirtle = squirtle.levantarPesas(5)
    assertEquals("Dormido",newSquirtle.estado.getNombre())
    assertEquals(130, newSquirtle.getExperiencia()) 
    
     // levantar pesas en estado paralizado
    val especieHitmonchan = new Especie("Hitmonchan", null, null) with Lucha
    val lucha = new Pokemon("Lucha",30,50,100,60,40, especieHitmonchan, new Paralizado())
    assertEquals(30, pepe.getExperiencia())
    val newLucha = lucha.levantarPesas(10)
    assertEquals("KO",newLucha.estado.getNombre())
    assertEquals(30,newLucha.getExperiencia())
    
    // levantar pesas en estado KO
    val especieBlastoise = new Especie("Blastoise", null, null) with Agua
    val blastoise = new Pokemon("Blastoise",130,50,100,60,50, especieBlastoise, new KO())
    val newBlastoise = blastoise.levantarPesas(5)
    assertEquals("KO",newBlastoise.estado.getNombre())
    assertEquals(50, newSquirtle.getEnergia())
  }
  
  @Test
  def DescansarPorDiferentesEstadosPokemon(): Unit ={
    
     // descanza 1 una vez
     val newPepe = pepe.descansar()
     assertEquals("Dormido", newPepe.estado.getNombre())
     assertEquals(100, newPepe.getEnergia())
     
     // descanse en estado Ko
     val especieCharmeleon = new Especie("Charmeleon", null, null) with Fuego
     val charmeleon = new Pokemon("Pepe",30,40,100,60,40, especieCharmeleon, new KO())
     val charmeleon2 = charmeleon.descansar()
     assertEquals("KO", charmeleon2.estado.getNombre())
     assertEquals(30, charmeleon2.getExperiencia())
     assertEquals(40, charmeleon2.getEnergia())
     assertEquals(100, charmeleon2.getEnergiaMax())
     assertEquals(60, charmeleon2.getFuerza())
     assertEquals(40, charmeleon2.getVelocidad())
     
     //descansa en estado Dormido
     val especieCharizard = new Especie("Charizard", null, null) with Fuego
     val charizard = new Pokemon("Charizard",30,40,100,60,40, especieCharmeleon, new Dormido())
     val charizard1 = charizard.descansar()
     assertEquals("Dormido", charizard1.estado.getNombre())
     assertEquals(30, charizard1.getExperiencia())
     assertEquals(40, charizard1.getEnergia())
     assertEquals(100, charizard1.getEnergiaMax())
     assertEquals(60, charizard1.getFuerza())
     assertEquals(40, charizard1.getVelocidad())
 
     val charizard2 = charizard1.descansar()
     assertEquals("Dormido", charizard2.estado.getNombre())
     
     // descansa 3 veces en estado dormido
     val charizard3 = charizard2.descansar()
     assertEquals(null, charizard3.estado)
     
     // descansar en estado Paralizado
     val especieArcanine = new Especie("Arcanine", null, null) with Fuego
     val arcanine = new Pokemon("Arcanine",30,40,100,60,40, especieArcanine, new Paralizado())
     val newArcanine = arcanine.descansar()
     assertEquals("Paralizado", newArcanine.estado.getNombre())
     assertEquals(40, newArcanine.getEnergia())
  }
}