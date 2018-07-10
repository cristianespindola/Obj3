

abstract class Estado {
  
  def getNombre(): String
  
  def estadoXSubirPesas(): Estado
  
  def descansar(pokemon: Pokemon) : Pokemon
  
  def nadar(pokemon: Pokemon, minutos: Int): Pokemon
  
  def levantarPesas(pokemon: Pokemon, kilos: Int): Pokemon
}