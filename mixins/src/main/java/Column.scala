

abstract class Column[T](var nombreColumna: String){
  
  
  def asc(): String ={
    
    val orderSql = "order by " + this.nombreColumna + " asc;"
    return orderSql
  }
  
  
  def desc(): String ={
    
    val orderSql = "order by " + this.nombreColumna + " desc;"
    return orderSql
  }
  
  def sql(): String
  
}


class Paquete[T,R] (val columna: Column[T], val i: R) {
 
    
} 


case class IntColumn(val unNombreC: String) extends Column[Int](unNombreC){
  
  
  def sql():String = unNombreC+ " int";
  
    
  def > (i:Int) : Condition[Int,Int] = {
   val paq : Paquete[Int,Int] = new Paquete[Int,Int](this,i)
   val mayor = new Mayor[Int,Int](paq)
   return mayor
  }
  
  def < (i:Int) : Condition[Int,Int] = {
   val paq : Paquete[Int,Int] = new Paquete[Int,Int](this,i)
   val menor = new Menor[Int,Int](paq)
   return menor
  }
  
  def >= (i:Int) : Condition[Int,Int] = {
   val paq : Paquete[Int,Int] = new Paquete[Int,Int](this,i)
   val mayorigual = new MayorIgual[Int,Int](paq)
   return mayorigual
  }
  
  def <= (i:Int) : Condition[Int,Int] = {
   val paq : Paquete[Int,Int] = new Paquete[Int,Int](this,i)
   val menorigual = new MenorIgual[Int,Int](paq)
   return menorigual
  }
  
  def === (i:Int) : Condition[Int,Int] = {
   val paq : Paquete[Int,Int] = new Paquete[Int,Int](this,i)
   val igual = new Igual[Int,Int](paq)
   return igual
  }
  
  def =!= (i:Int) : Condition[Int,Int] = {
   val paq : Paquete[Int,Int] = new Paquete[Int,Int](this,i)
   val distinto = new Distinto[Int,Int](paq)
   return distinto
  }
}  



case class StringColumn(val unNombreC: String) extends Column[String](unNombreC){
  
  def sql():String = unNombreC+ " varchar(255)";
    
  def > (i:Int) : Unit = {
   println("ERROR: no se puede utilizar este operando en esta columna")
  }
  
  def < (i:Int) : Unit = {
   println("ERROR: no se puede utilizar este operando en esta columna")
  }
  
  def >= (i:Int) : Unit = {
   println("ERROR: no se puede utilizar este operando en esta columna")
  }
  
  def <= (i:Int) : Unit = {
   println("ERROR: no se puede utilizar este operando en esta columna")
  }
  
  def === (i:String) : Condition[String,String] = {
   val paq : Paquete[String,String] = new Paquete[String,String](this,i)
   val igual = new Igual[String,String](paq)
   return igual
  }
  
  def =!= (i:String) : Condition[String,String] = {
   val paq : Paquete[String,String] = new Paquete[String,String](this,i)
   val distinto = new Distinto[String,String](paq)
   return distinto
  }
}
 


  /*
  def < (i: Int) : Condition = {
 
    val resultado = this.nombreC + "< " + i
    return resultado
 }
  
  def > (i: Int) : String = {
    
    val resultado = this.nombreC + "> " + i
    return resultado
  }
*/ 

