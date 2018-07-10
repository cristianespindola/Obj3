

class Rutina(val actividades: List[Actividad]) {
  
  def realizar(pokemon: Pokemon): Pokemon ={
    this.actividades match{
      case Nil => return pokemon
      case (x::xs) => new Rutina(xs).realizar(pokemon.realizarActividad(x))
    }
  }
  /*
  var newPok:Pokemon
     actividades.foreach{
        a =>  newPok = pokemon.realizarActividad(a)}
     return newPok*/
}