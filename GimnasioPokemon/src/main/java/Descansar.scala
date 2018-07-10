

class Descansar extends Actividad{
  
  override def realizar(pokemon: Pokemon): Pokemon ={
    if(pokemon.estado == null){
      val newPok = new Pokemon(pokemon.nombre,
                         pokemon.experiencia,
                         pokemon.energiaMax,
                         pokemon.energiaMax,
                         pokemon.fuerza, 
                         pokemon.velocidad,
                         pokemon.especie, 
                         pokemon.verificarEstadoDormido)
      return newPok
    }else{return pokemon.estado.descansar(pokemon)}  
  }
  
}