

class Pokemon (val nombre: String, val experiencia: Int, val energia: Int, val energiaMax: Int, val fuerza: Int, val velocidad: Int, val especie: Especie, val estado: Estado){
  
  def getNombre(): String = return nombre;
   
  def getExperiencia(): Int = return experiencia;
    
  def getEnergia(): Int = return energia;
   
  def getEnergiaMax(): Int = return energiaMax;
   
  def getFuerza(): Int = return fuerza;
   
  def getVelocidad(): Int = return velocidad;
  
  def getEspecie(): Especie = return especie;
  
  def getEstado(): Estado = return estado;
  
  def realizarActividad(actividad: Actividad): Pokemon ={
    return actividad.realizar(this)
  }
  
  def realizarRutina(rutina: Rutina): Pokemon ={
    return rutina.realizar(this)
  }
  def descansar(): Pokemon ={
    if(estado == null){
      val newPok = new Pokemon(this.nombre,
                         this.experiencia,
                         this.energiaMax,
                         this.energiaMax,
                         this.fuerza, 
                         this.velocidad,
                         this.especie, 
                         this.verificarEstadoDormido)
      return newPok
    }else{return estado.descansar(this)}  
  }
  
  /*def levantarPesas( kilos: Int): Pokemon ={
    if(this.estado == null){
      
        val newPok: Pokemon = new Pokemon(this.nombre, 
                this.especie.aumentarExpXLevantarPesas(this.experiencia, this.fuerza, kilos),
                this.especie.perderEnergiaXExcesoDeFuerza(this.energia,this.fuerza, kilos),
                this.energiaMax, this.fuerza, this.velocidad,
                this.especie,
                this.especie.cambiarEstadoXSubirPesas(this.estado, this.fuerza, kilos))
        
        if((newPok.especie.condicionAEvolucior != null) && (newPok.especie.condicionAEvolucior.cumpleCondicionAEvolucionar(newPok))){
          return newPok.evolucionar()
        }else{
        return newPok}
    }else{
      return estado.levantarPesas(this, kilos)
    }
  }
  
  def nadar(minutos: Int): Pokemon ={
    if(this.estado == null){
      val newPok: Pokemon = new Pokemon(this.nombre,
          this.especie.aumentarExpXNadar(this.experiencia, minutos),
          this.especie.perderEnergiaXNadar(this.energia,minutos),
          this.energiaMax, 
          this.fuerza,
          this.especie.aumentarVelocidadXNadarAgua(this.velocidad, minutos), 
          this.especie, 
          this.especie.cambiarEstadoXNadar(this.estado))
      
      if((newPok.especie.condicionAEvolucior != null) && (newPok.especie.condicionAEvolucior.cumpleCondicionAEvolucionar(newPok))){
          return newPok.evolucionar()
        }else{
        return newPok}
    }
    else{
      return estado.nadar(this, minutos)
    }
        
  }
  */
  def verificarEstadoDormido(): Estado ={
   if(this.energia < (energiaMax / 2)){
     return new Dormido()
     }else{
       return estado} 
  } 
  
  def evolucionar(): Pokemon ={
    return new Pokemon(
                  this.nombre,
                  this.experiencia * 3,
                  this.energia,
                  this.energiaMax,
                  this.fuerza,
                  this.velocidad,
                  this.especie.getSigEvoluvion(),
                  this.estado
                      )
  }
  
  def intercambio(): Pokemon ={
    if(this.especie.condicionAEvolucior != null && this.especie.condicionAEvolucior.cumpleCondicionAEvolucionar(this)){
      return this.evolucionar()
    }else{
      return new Pokemon(
                  this.nombre,
                  this.experiencia,
                  this.energia - 10,
                  this.energiaMax,
                  this.fuerza,
                  this.velocidad,
                  this.especie,
                  this.estado
                      )
    }
  }
}