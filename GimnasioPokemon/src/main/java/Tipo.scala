

trait Tipo extends Especie{
  override def getNombre(): String = return nombre;
  
  override def getNombreTipo(): String = "LALALALALALA"
  
  override def aumentarExpXLevantarPesas(experiencia: Int, fuerza:Int, kilos: Int): Int={
    if(kilos > fuerza * 10){return experiencia}
    else{
      return experiencia + kilos}
    }
  
  override def perderEnergiaXExcesoDeFuerza(energia: Int, fuerza: Int, kilos: Int): Int={
    if(kilos > fuerza * 10){return energia - 10}
    else{
      return energia}
    }
  
  override def aumentarExpXNadar(experiencia: Int, minutos: Int): Int ={
    return experiencia + 200 
  }
  
  override def perderEnergiaXNadar(energia: Int, minutos: Int): Int ={
     return energia - minutos
  }
  
  override def aumentarVelocidadXNadarAgua(velocidad: Int, minutos: Int): Int ={
    return velocidad 
  }
  
  override  def cambiarEstadoXNadar(estado: Estado): Estado ={
    return estado
  }
  
  override def cambiarEstadoXSubirPesas(estado: Estado, fuerza: Int, kilos: Int): Estado ={
    if(kilos > fuerza * 10){
      if(estado == null){
        return new Paralizado()         
      }else{
     return estado.estadoXSubirPesas()}
    }
    else{
      return estado
    }
  }
}

trait Fuego extends Tipo{
  override def getNombreTipo: String = "Fuego"
  
  override  def cambiarEstadoXNadar(estado: Estado): Estado ={
    return new KO()
  }
}

trait Agua extends Tipo{
  override def aumentarVelocidadXNadarAgua(velocidad: Int, minutos: Int): Int ={
    return velocidad + minutos
  }
}

trait Lucha extends Tipo{
  override def aumentarExpXLevantarPesas(experiencia: Int, fuerza: Int, kilos: Int): Int ={
    if(kilos > fuerza * 10){return experiencia}
    else{
      return experiencia + kilos *2}
  }
  
}

trait Tierra extends Tipo{
   override  def cambiarEstadoXNadar  (estado: Estado): Estado ={
    return new KO()
  }
}

trait Fantasma extends Tipo{
  override def aumentarExpXLevantarPesas(experiencia: Int, fuerza: Int,  Kilos: Int): Int ={
    print("No Puede Levantar Pesas")
    return experiencia
  }
  
}
trait Titan extends Tipo{}



trait Condiciones extends Condicion{
    override def cumpleCondicionAEvolucionar(newPok: Pokemon): Boolean = false
}

trait Experiencia extends Condiciones{
  
  override def cumpleCondicionAEvolucionar(newPok: Pokemon): Boolean ={
    return super.getExperiencia < newPok.experiencia
  }

}

trait Intercambio extends Condiciones{
  override def cumpleCondicionAEvolucionar(newPok: Pokemon): Boolean = true
}






