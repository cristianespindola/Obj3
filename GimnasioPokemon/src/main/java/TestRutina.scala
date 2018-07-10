
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
  def RutinaValida(): Unit={
    val actividades = List(levantarPesas,nadar,descansar)
    val rutinaValida = new Rutina(actividades)
    val newHitmonchan = hitmonchan.realizarRutina(rutinaValida)
    assertEquals(100,newHitmonchan.getEnergia())
    assertEquals(250,newHitmonchan.getExperiencia())
    
    val actividades2 = List(levantarPesas,nadar)
    val rutinaValida2 = new Rutina(actividades2)
    val newHitmonchan2 = hitmonchan.realizarRutina(rutinaValida2)
    assertEquals(45,newHitmonchan2.getEnergia())
    assertEquals(250,newHitmonchan2.getExperiencia())
    
    
    val actividades3 = List(descansar,nadar,nadar)
    val rutinaValida3 = new Rutina(actividades3)
    val newMachamp = machamp.realizarRutina(rutinaValida3)
    assertEquals(100,newMachamp.getEnergia())
    assertEquals(130,newMachamp.getExperiencia())
    
    val actividades4 = List(descansar,nadar,nadar,nadar)
    val rutinaValida4 = new Rutina(actividades4)
    val newMachamp2 = machamp.realizarRutina(rutinaValida4)
    assertEquals(95,newMachamp2.getEnergia())
    assertEquals(330,newMachamp2.getExperiencia())
  }
}