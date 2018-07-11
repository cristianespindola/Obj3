
import org.junit.Assert._
import org.junit.Test
class TestRutina {
  
  val descansar = new Descansar()
  
  val levantarPesas = new LevantarPesas(10)
  
  val nadar = new Nadar(5) 
  
  val especieCharmander = new Especie("Charmander", null, null) with Fuego
  val pepe = new Pokemon("Pepe",30,50,100,60,40, especieCharmander, null)
  
  val especieHitmonchan = new Especie("Hitmonchan", null, null) with Pelea
  val hitmonchan = new Pokemon("Hitmonchan",30,50,100,60,40, especieHitmonchan, null)
  
  val especieGastly = new Especie("Gastly", null, null) with Fantasma
  val fantasmin = new Pokemon("Fantasmin",30,50,100,60,40, especieGastly, null)
  
  val especieMachamp = new Especie("Machamp", null, null) with Pelea
  val machamp = new Pokemon("Machamp",130,45,100,80,40, especieMachamp, null)
  
  val especieSquirtle = new Especie("Squirtle", null, null) with Agua
  val squirtle = new Pokemon("Squirtle",130,50,100,80,50, especieSquirtle, null)
  
  @Test
  def rutinaValida(): Unit={
    
    // turina simple
    val actividades = List(levantarPesas,nadar,descansar)
    val rutinaValida = new Rutina("levantarNadarYDescansar",actividades)
    val newHitmonchan = hitmonchan.realizarRutina(rutinaValida)
    assertEquals(100,newHitmonchan.getEnergia())
    assertEquals(250,newHitmonchan.getExperiencia())
    
    // rutina sin descansar
    val actividades2 = List(levantarPesas,nadar)
    val rutinaValida2 = new Rutina("levantarNadar",actividades2)
    val newHitmonchan2 = hitmonchan.realizarRutina(rutinaValida2)
    assertEquals(45,newHitmonchan2.getEnergia())
    assertEquals(250,newHitmonchan2.getExperiencia())
    
    //rutina durante descanso
    val actividades3 = List(descansar,nadar,nadar)
    val rutinaValida3 = new Rutina("descarnsaNadar",actividades3)
    val newMachamp = machamp.realizarRutina(rutinaValida3)
    assertEquals(100,newMachamp.getEnergia())
    assertEquals(130,newMachamp.getExperiencia())
    
    //rutina despues de descansar y valida
    val actividades4 = List(descansar,nadar,nadar,nadar)
    val rutinaValida4 = new Rutina("descansarLuegoNadar",actividades4)
    val newMachamp2 = machamp.realizarRutina(rutinaValida4)
    assertEquals(95,newMachamp2.getEnergia())
    assertEquals(330,newMachamp2.getExperiencia())
  }
  
  @Test
  def rutinaInvalida(): Unit ={
    
    // rutina sin poder hacer pesas, solo hace nadar y descansar
    val actividades = List(levantarPesas,nadar,descansar)
    val rutinaInvalida = new Rutina("levantarNadarDescansas",actividades)
    val newFantasmin = fantasmin.realizarRutina(rutinaInvalida)
    assertEquals(100,newFantasmin.getEnergia())
    assertEquals(230,newFantasmin.getExperiencia()) // puntos solo de nadar
   
    // rutina sin perder ni ganar energia por no poder nada y quedar KO 
    val actividades2 = List(nadar,descansar)
    val rutinaInvalida2 = new Rutina("invalido",actividades2)
    val newPepe = pepe.realizarRutina(rutinaInvalida2)
    assertEquals(50,newPepe.getEnergia())
    assertEquals(30,newPepe.getExperiencia())
  }
}