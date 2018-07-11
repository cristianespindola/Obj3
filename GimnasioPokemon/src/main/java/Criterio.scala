

abstract class Criterio {
  
  def caracAVerificar(pokemon:Pokemon):Int
  
  def caracPorVerificar(pokemon: Pokemon): Int
  
  def calcular(caracActual:Int, caracAVerificar:Int): Boolean
}