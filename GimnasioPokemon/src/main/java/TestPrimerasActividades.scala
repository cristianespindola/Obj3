
import org.junit.Assert._
import org.junit.Test
class TestPrimerasActividades {
  
  val especieCharmander = new Especie("Charmander", null, null) with Fuego
  val pepe = new Pokemon("Pepe",30,50,100,60,40, especieCharmander, null)
  
  val especieHitmonchan = new Especie("Hitmonchan", null, null) with Pelea
  val hitmonchan = new Pokemon("Hitmonchan",30,50,100,60,40, especieHitmonchan, null)
  
  val especieGastly = new Especie("Gastly", null, null) with Fantasma
  val fantasmin = new Pokemon("Fantasmin",30,50,100,60,40, especieGastly, null)
  
  val especieMachamp = new Especie("Machamp", null, null) with Pelea
  val machamp = new Pokemon("Machamp",130,50,100,80,40, especieMachamp, null)
  
  val especieSquirtle = new Especie("Squirtle", null, null) with Agua
  val squirtle = new Pokemon("Squirtle",130,50,100,80,50, especieSquirtle, null)
  
  @Test
  def actividadDescansar(): Unit ={
    val descansar = new Descansar()
    assertEquals(50, pepe.getEnergia())
    val newPepe = pepe.realizarActividad(descansar)
    assertEquals(100,newPepe.getEnergia())
  }
  
  @Test
  def levantarPesasPorPokemosDiferentesTiposYPesas(): Unit = {
    // levantar pesas en estado normal
    val levantarPesas = new LevantarPesas(10)
    assertEquals(30, pepe.getExperiencia())
    val newPepe = pepe.realizarActividad(levantarPesas)
    assertEquals(40,newPepe.getExperiencia())
    
    val especieHitmonchan = new Especie("Hitmonchan", null, null) with Pelea
    val lucha = new Pokemon("Lucha",30,50,100,60,40, especieHitmonchan, null)
    assertEquals(30, pepe.getExperiencia())
    val newLucha = lucha.realizarActividad(levantarPesas)
    assertEquals(50,newLucha.getExperiencia())
    
    // levantar pesas con un tipo de pokemon invalido, Fantasma
    val especieGastly = new Especie("Gastly", null, null) with Fantasma
    val fantasmin = new Pokemon("Fantasmin",30,50,100,60,40, especieGastly, null)
    assertEquals(30, fantasmin.getExperiencia())
    val newFantasmin = fantasmin.realizarActividad(levantarPesas)
    assertEquals(30,newFantasmin.getExperiencia()) // queda con la misma experiencia x no poder levantar pesas
    
    // levanta pesas un pokemon tipo lucha, y pierde energia por exceso de fuerza
    val levantarMasPesas = new LevantarPesas(801)
    val especieMachamp = new Especie("Machamp", null, null) with Pelea
    val machamp = new Pokemon("Machamp",130,50,100,80,40, especieMachamp, null)
    assertEquals(130, machamp.getExperiencia())
    val newMachamp = machamp.realizarActividad(levantarMasPesas)
    assertEquals(130,newMachamp.getExperiencia())
    assertEquals(40, newMachamp.getEnergia())
  }
  
  @Test
  def nadarPorDiferentesTiposDePokemos(): Unit ={
    
    val nadar = new Nadar(5) 
    val especieMachamp = new Especie("Machamp", null, null) with Pelea
    val machamp = new Pokemon("Machamp",130,50,100,80,40, especieMachamp, null)
    assertEquals(130, machamp.getExperiencia())
    val newMachamp = machamp.realizarActividad(nadar)
    assertEquals(330,newMachamp.getExperiencia())
    assertEquals(45, newMachamp.getEnergia())
    
    val especieSquirtle = new Especie("Squirtle", null, null) with Agua
    val squirtle = new Pokemon("Squirtle",130,50,100,80,50, especieSquirtle, null)
    assertEquals(130, squirtle.getExperiencia())
    val newSquirtle = squirtle.realizarActividad(nadar)
    assertEquals(330,newSquirtle.getExperiencia())
    assertEquals(45, newSquirtle.getEnergia())
    assertEquals(55, newSquirtle.getVelocidad)
  }
  /*val especieCharmander = new Especie("Charmander", null, null) with Fuego
  val pepe = new Pokemon("Pepe",30,50,100,60,40, especieCharmander, null)
  
  @Test
  def DadoUnPokemonVeoSusAtributos(): Unit ={
    assertEquals("Pepe", pepe.getNombre())
    assertEquals(30, pepe.getExperiencia())
    assertEquals(50, pepe.getEnergia())
    assertEquals(100, pepe.getEnergiaMax())
    assertEquals(60, pepe.getFuerza())
    assertEquals(40, pepe.getVelocidad())
    assertEquals("Charmander", pepe.getEspecie().getNombre())
    assertEquals("Fuego", pepe.getEspecie().getNombreTipo())
  }
  
  @Test
  def DadoUnPOkemosConTipoInvalidoTiraLALALA(): Unit ={
    val escepecieTitan = new Especie("Titan", null, null) with Titan
    val titan = new Pokemon("Tit",30,50,100,60,40, escepecieTitan, null)
    
    assertEquals("LALALALALALA", titan.getEspecie().getNombreTipo())
  }
  
  @Test
  def DadoUnPokemosCon50DeEnergiaY100DeEnergiaMAxAlDescansarTiene100DeEnergia(): Unit ={
    assertEquals(50, pepe.getEnergia())
    val newPepe = pepe.descansar()
    assertEquals(100,newPepe.getEnergia())
  }
  
  @Test
  def LevantarPesasPorPokemosDiferentesTiposYPesas(): Unit = {
    assertEquals(30, pepe.getExperiencia())
    val newPepe = pepe.levantarPesas(10)
    assertEquals(40,newPepe.getExperiencia())
    
    val especieHitmonchan = new Especie("Hitmonchan", null, null) with Lucha
    val lucha = new Pokemon("Lucha",30,50,100,60,40, especieHitmonchan, null)
    assertEquals(30, pepe.getExperiencia())
    val newLucha = lucha.levantarPesas(10)
    assertEquals(50,newLucha.getExperiencia())
    
    val especieGastly = new Especie("Gastly", null, null) with Fantasma
    val fantasmin = new Pokemon("Fantasmin",30,50,100,60,40, especieGastly, null)
    assertEquals(30, fantasmin.getExperiencia())
    val newFantasmin = fantasmin.levantarPesas(10)
    assertEquals(30,newFantasmin.getExperiencia())
    
    val especieMachamp = new Especie("Machamp", null, null) with Lucha
    val machamp = new Pokemon("Machamp",130,50,100,80,40, especieMachamp, null)
    assertEquals(130, machamp.getExperiencia())
    val newMachamp = machamp.levantarPesas(801)
    assertEquals(130,newMachamp.getExperiencia())
    assertEquals(40, newMachamp.getEnergia())
    
  }
  
    @Test
    def NadarPorDiferentesTiposDePokemos(): Unit ={
     
    val especieMachamp = new Especie("Machamp", null, null) with Lucha
    val machamp = new Pokemon("Machamp",130,50,100,80,40, especieMachamp, null)
    assertEquals(130, machamp.getExperiencia())
    val newMachamp = machamp.nadar(5)
    assertEquals(330,newMachamp.getExperiencia())
    assertEquals(45, newMachamp.getEnergia())
    
    val especieSquirtle = new Especie("Squirtle", null, null) with Agua
    val squirtle = new Pokemon("Squirtle",130,50,100,80,50, especieSquirtle, null)
    assertEquals(130, squirtle.getExperiencia())
    val newSquirtle = squirtle.nadar(5)
    assertEquals(330,newSquirtle.getExperiencia())
    assertEquals(45, newSquirtle.getEnergia())
    assertEquals(55, newSquirtle.getVelocidad)
  }*/
  
}