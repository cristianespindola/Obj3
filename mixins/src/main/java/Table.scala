import org.junit.Test
import org.junit.Assert._
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import org.h2.tools.DeleteDbFiles;

abstract case class Table[R](val nombreT: String){
  
  type ResultType = R
  type MappingType 
   
  
  val generadorTabla: GeneradorSQL = new GeneradorSQL()
  
  
  def generarSQL: String= {
    return this.generadorTabla.crearTablaSQL(this)
  }
  
  def *(): Seq[Column[_]]
  
  def toColumnValues(a: ResultType): Seq[(Column[_], Any)] = ???
  
  def crearTabla():Unit ={
    try {     
   DeleteDbFiles.execute("~", this.nombreT, true);    
   }catch {
           case e :SQLException => e.printStackTrace();
        }
    var connection :Connection =DriverManager.getConnection( "jdbc:h2:~/"+nombreT);
    val CreateTableSql :String = this.generarSQL
    
    try {
   connection.setAutoCommit(false);
   
   var createPreparedStatement: PreparedStatement = null;
   
   createPreparedStatement = connection.prepareStatement(CreateTableSql);
   createPreparedStatement.executeUpdate();
   createPreparedStatement.close();
   
   connection.commit();
  }catch  {
     case e: SQLException => System.out.println("Exception Message " + e.getLocalizedMessage());
     case e2 : Exception =>  e2.printStackTrace();
        } finally {
            connection.close();
        }  
}
   
}


abstract class  Condition[T,R](val paquete: Paquete[T,R]){
  
  def toSqlPrimitive(value: Any): Any = {
    value match {
      case v: String => "'" +value+"'" //s""""${value}""""
      case a: Int => a 
    }
  }
  
  def generarSQL(): String 
}


class Menor[T,R](val unPaquete: Paquete[T,R]) extends Condition[T,R](unPaquete){
  
  def generarSQL() : String = return " WHERE " + unPaquete.columna.nombreColumna + " < " + this.toSqlPrimitive(unPaquete.i) 
}


class Mayor[T,R](val unPaquete: Paquete[T,R]) extends Condition[T,R](unPaquete){
  def generarSQL() : String = return " WHERE " + unPaquete.columna.nombreColumna + " > " +this.toSqlPrimitive(unPaquete.i)//"'"+ unPaquete.i+"'"
}


class Igual[T,R](val unPaquete: Paquete[T,R]) extends Condition[T,R](unPaquete){
  def generarSQL() : String = return " WHERE " + unPaquete.columna.nombreColumna + " = " +this.toSqlPrimitive(unPaquete.i)// "'"+unPaquete.i+"'"
}

class MayorIgual[T,R](val unPaquete: Paquete[T,R]) extends Condition[T,R](unPaquete){
  def generarSQL() : String = return " WHERE " + unPaquete.columna.nombreColumna + " >= " +this.toSqlPrimitive(unPaquete.i)//"'"+ unPaquete.i+"'"
}

class MenorIgual[T,R](val unPaquete: Paquete[T,R]) extends Condition[T,R](unPaquete){
  def generarSQL() : String = return " WHERE " + unPaquete.columna.nombreColumna + " <= " +this.toSqlPrimitive(unPaquete.i)//"'"+ unPaquete.i+"'"
}

class Distinto[T,R](val unPaquete: Paquete[T,R]) extends Condition[T,R](unPaquete){
  def generarSQL() : String = return " WHERE " + unPaquete.columna.nombreColumna + " <> " +this.toSqlPrimitive(unPaquete.i)//"'"+ unPaquete.i+"'"
}

