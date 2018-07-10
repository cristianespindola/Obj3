

class Paralizado extends Estado {
  
  override def getNombre(): String ={
    return "Paralizado"
  }
  
  override def estadoXSubirPesas(): Estado = return new KO()
  
   override def descansar(pokemon: Pokemon) : Pokemon = {
    print("No Puede Descansar")
    return pokemon
  }
   
   override def nadar(pokemon: Pokemon, minutos: Int): Pokemon = {
    print("No Puede Nadar")
    return pokemon
  }
  
   override  def levantarPesas(pokemon: Pokemon, kilos: Int): Pokemon = {
     return new Pokemon(pokemon.nombre, 
                pokemon.experiencia,
                pokemon.energia - 10,
                pokemon.energiaMax, 
                pokemon.fuerza,
                pokemon.velocidad,
                pokemon.especie,
                new KO())
   }
}