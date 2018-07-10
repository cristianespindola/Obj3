

class KO extends Estado{
  
  override def getNombre(): String ={
    return "KO"
  }
  
  override def estadoXSubirPesas(): Estado =return this
  
  override def descansar(pokemon: Pokemon) : Pokemon = return pokemon
  
  override def nadar(pokemon: Pokemon, minutos: Int): Pokemon = return pokemon
  
  override  def levantarPesas(pokemon: Pokemon, kilos: Int): Pokemon = return pokemon
}