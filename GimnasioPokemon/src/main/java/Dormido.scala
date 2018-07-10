

class Dormido extends Estado{
  
  var cant:Int = 0
  
  override def getNombre(): String ={
    return "Dormido"
  }
  
  override def estadoXSubirPesas(): Estado =return this
  
   override def descansar(pokemon: Pokemon) : Pokemon = {
    if(cant == 2){
      cant = 0
      return new Pokemon(pokemon.nombre,
                         pokemon.experiencia,
                         pokemon.energiaMax,
                         pokemon.energiaMax,
                         pokemon.fuerza, 
                         pokemon.velocidad,
                         pokemon.especie, 
                         null)
    }else{
      cant += 1
      return pokemon
    }
  }
  
  override def nadar(pokemon: Pokemon, minutos: Int): Pokemon ={
    if(cant == 2){
      cant = 0
      return new Pokemon(pokemon.nombre,
                         pokemon.especie.aumentarExpXNadar(pokemon.experiencia, minutos),
                         pokemon.especie.perderEnergiaXNadar(pokemon.energia, minutos),
                         pokemon.energiaMax,
                         pokemon.fuerza, 
                         pokemon.especie.aumentarVelocidadXNadarAgua(pokemon.velocidad, minutos),
                         pokemon.especie, 
                         null)
    }else{
      cant += 1
      return pokemon
    }
  }
  
   def levantarPesas(pokemon: Pokemon, kilos: Int): Pokemon ={
     if(cant == 2){
       cant = 0
       return new Pokemon(pokemon.nombre, 
                pokemon.especie.aumentarExpXLevantarPesas(pokemon.experiencia, pokemon.fuerza, kilos),
                pokemon.especie.perderEnergiaXExcesoDeFuerza(pokemon.energia,pokemon.fuerza, kilos),
                pokemon.energiaMax, 
                pokemon.fuerza, 
                pokemon.velocidad,
                pokemon.especie,
                pokemon.especie.cambiarEstadoXSubirPesas(pokemon.estado, pokemon.fuerza, kilos))
     }else{
       cant += 1
       return pokemon
     }
   }
   
}