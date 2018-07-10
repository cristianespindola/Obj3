

abstract class Especie (val nombre: String, val condicionAEvolucior:Condicion, val sigEvolucion: Especie){
  
  def getNombre(): String = return nombre
  
  def getCondicionAEvolucior(): Condicion = return condicionAEvolucior
  
  def getSigEvoluvion(): Especie = return sigEvolucion

  def getNombreTipo(): String = "LALALALALALA"
  
  def aumentarExpXLevantarPesas(experiencia: Int, fuerza: Int, kilos: Int): Int
  
  def perderEnergiaXExcesoDeFuerza(energia: Int, fuerza: Int, kilos: Int): Int
  
  def aumentarExpXNadar(experiencia: Int, minutos: Int): Int
  
  def perderEnergiaXNadar(energia: Int, minutos: Int): Int
  
  def aumentarVelocidadXNadarAgua(velocidad: Int, minutos: Int): Int
  
  def cambiarEstadoXNadar(estado: Estado): Estado
  
  def cambiarEstadoXSubirPesas(estado: Estado, fuerza: Int, kilos: Int): Estado

}