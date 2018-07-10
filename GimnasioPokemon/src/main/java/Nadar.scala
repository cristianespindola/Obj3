

class Nadar(minutos: Int) extends Actividad {
  
  override def realizar(pokemon: Pokemon): Pokemon ={
    if(pokemon.estado == null){
      val newPok: Pokemon = new Pokemon(pokemon.nombre,
          pokemon.especie.aumentarExpXNadar(pokemon.experiencia, minutos),
          pokemon.especie.perderEnergiaXNadar(pokemon.energia,minutos),
          pokemon.energiaMax, 
          pokemon.fuerza,
          pokemon.especie.aumentarVelocidadXNadarAgua(pokemon.velocidad, minutos), 
          pokemon.especie, 
          pokemon.especie.cambiarEstadoXNadar(pokemon.estado))
      
      if((newPok.especie.condicionAEvolucior != null) && (newPok.especie.condicionAEvolucior.cumpleCondicionAEvolucionar(newPok))){
          return newPok.evolucionar()
        }else{
        return newPok}
    }
    else{
      return pokemon.estado.nadar(pokemon, minutos)
    }
  }
  
}