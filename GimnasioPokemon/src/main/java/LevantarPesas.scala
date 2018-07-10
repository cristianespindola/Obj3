

class LevantarPesas(val kilos:Int) extends Actividad {
  
  override def realizar(pokemon: Pokemon): Pokemon ={
    if(pokemon.estado == null){
      
        val newPok: Pokemon = new Pokemon(pokemon.nombre, 
                pokemon.especie.aumentarExpXLevantarPesas(pokemon.experiencia, pokemon.fuerza, kilos),
                pokemon.especie.perderEnergiaXExcesoDeFuerza(pokemon.energia,pokemon.fuerza, kilos),
                pokemon.energiaMax, pokemon.fuerza, pokemon.velocidad,
                pokemon.especie,
                pokemon.especie.cambiarEstadoXSubirPesas(pokemon.estado, pokemon.fuerza, kilos))
        
        if((newPok.especie.condicionAEvolucior != null) && (newPok.especie.condicionAEvolucior.cumpleCondicionAEvolucionar(newPok))){
          return newPok.evolucionar()
        }else{
        return newPok}
    }else{
      return pokemon.estado.levantarPesas(pokemon, kilos)
    }
  }
  
}