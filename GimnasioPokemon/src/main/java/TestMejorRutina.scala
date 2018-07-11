
import org.junit.Assert._
import org.junit.Test
class TestMejorRutina {
  
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
    
    // turina simple criterio mayor nivel
    val actividades1 = List(levantarPesas,nadar,descansar)
    val actividades2 = List(levantarPesas,nadar)
    val actividades3 = List(descansar,nadar,nadar)
    
    val rutina1 = new Rutina("levantarNadarYDescansar",actividades1)
    val rutina2 = new Rutina("levantarNadar",actividades2)
    val rutina3 = new Rutina("descarnsaNadar",actividades3)
    
    val rutinas = List(rutina2,rutina3)
    
    val criterioAnalizar = new Criterio() with MayorNivel
    val analizadorRutina = new AnalizadorRutina(criterioAnalizar)
    val rutinaAnalizada = analizadorRutina.analizar(hitmonchan, rutinas)
    
    assertEquals("levantarNadar",rutinaAnalizada)
    
    //rutina simple criterio mayor energia
    val rutinas2 = List(rutina1,rutina2)
    val criterio2 = new Criterio() with MayorEnergia
    val analizadorRutina2 = new AnalizadorRutina(criterio2)
    val rutinaAnalizada2 = analizadorRutina2.analizar(hitmonchan, rutinas2)
    
    assertEquals("levantarNadarYDescansar", rutinaAnalizada2)
    
  }
  
}