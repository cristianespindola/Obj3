

class AnalizadorRutina(val criterio: Criterio) {
  
  def analizar(pokemon:Pokemon, rutinas: List[Rutina]):String ={
    return (analisisPorCriterio(0, pokemon, rutinas)).nombre
    
  }
  
  def analisisPorCriterio(caracVerificar:Int, pokemon:Pokemon, rutinas:List[Rutina]): Rutina ={
    rutinas match{
      case (r)     => return rutinas.head 
      case (x::xs)  => val n = criterio.caracPorVerificar(x.realizar(pokemon))
        
        if(criterio.calcular(caracVerificar, n) ){
          return analisisPorCriterio(caracVerificar, pokemon, xs)
       }else{
               return analisisPorCriterio(n, pokemon, xs)
            }
    }
  }
  
}