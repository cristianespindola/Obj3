

object Perros extends Table[Perro]("perros"){
  
  type MappingType = this.type
  
  def nombre: StringColumn= new StringColumn("nombre")
    
  def edad: IntColumn = new IntColumn("edad")
    
  def * = Seq(nombre, edad)
  
  override def toColumnValues(p: Perro) = Seq((nombre, p.nombreP), (edad, p.edad))
}


object Main extends App {
  
  override def main(args : Array[String]) : Unit  = {
         
    /*
        var perros = Perros.sql
        println(perros)
    */    
        
        /*
        val viejitos:Query[String] = Query[Perro](Perros)
            .filter(Perros.edad > 12)
            .map(Perros.nombre)
            * 
            */
  }  
}

//override def sql : String = {"CREATE TABLE " + this.nombreT + " ( " + this.*._1.nombreC + " varchar(255)," + this.*._2.nombreC + " int );" }