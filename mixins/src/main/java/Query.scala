import org.junit.Test
import org.junit.Assert._
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import org.h2.tools.DeleteDbFiles;

case class Query[T <: Table[ResultType],ResultType](val m: T) {
  
    var selectFrom :String = "";
    var where :String  = "";
    var order :String = "";
    var limit: String = "";
    var offset : String = "";
    var unionSql :String = "";
    val generador : GeneradorSQL = new GeneradorSQL
    
    def actualizarQuery(sel:String, whe : String, ord :String, lim:String, off :String):Unit = {
      //this.selectFrom = sel;
      if (sel == ""){
        this.selectFrom = this.generador.crearSelecSimpleSql(m);
      }else{this.selectFrom = sel}
      this.where = whe;
      this.order = ord;
      this.limit = lim;
      this.offset = off;
    }
    def drop(i : Int): Query[Table[m.ResultType],m.ResultType] ={
      val queryRes : Query[Table[m.ResultType],m.ResultType] =this.copy(m);//new Query(m)
      //queryRes.limit = this.generador.crearSQLLimit(m,i)
      queryRes.actualizarQuery(this.selectFrom, this.where, this.order, this.generador.crearSQLLimit(m,i), this.offset)
      /*if (this.selectFrom == ""){
        queryRes.selectFrom = this.generador.crearSelecSimpleSql(m);
      }*/
      return queryRes
    }
    
    def take(i : Int): Query[Table[m.ResultType],m.ResultType] ={
      val queryRes : Query[Table[m.ResultType],m.ResultType] =this.copy(m);//new Query(m)
      //println("SEARA"+this.limit)
      queryRes.actualizarQuery(this.selectFrom, this.where, this.order, this.limit, this.generador.crearSQLOfset(m,i))
      /*queryRes.offset = this.generador.crearSQLOfset(m,i)
      if (this.selectFrom == ""){
        queryRes.selectFrom = this.generador.crearSelecSimpleSql(m);
      }else{queryRes.selectFrom = this.selectFrom}
      if(this.limit != ""){
        queryRes.limit = this.limit
      }*/
      return queryRes
    }
    def map[S] (f: T => Column[S]): Query[Table[S],S] = {
      
      val newQuery : Query[Table[S],S] = new Query(new Table[S](m.nombreT) {
      def *() = Seq(f(m))
      
    });
    val columna = newQuery.m.*().head
    println(this.generador.crearSelectSQL(columna, m))
    newQuery.selectFrom = this.generador.crearSelectSQL(columna, m)
    
    return newQuery
    
    }
    
    
    
    def sortby[TipoColumna,ValorAEvaluar] (f: T => String): Query[Table[m.ResultType],m.ResultType] ={
      val queryRes : Query[Table[m.ResultType],m.ResultType] =this.copy(m);//new Query(m)
      //f(m)
      queryRes.order = f(m)
      if (this.selectFrom == ""){
        queryRes.selectFrom = this.generador.crearSelecSimpleSql(m);
      }
      return queryRes
    }
    def filter[TipoColumna,ValorAEvaluar](f: T => Condition[TipoColumna,ValorAEvaluar]): Query[Table[m.ResultType],m.ResultType] ={
      val queryRes : Query[Table[m.ResultType],m.ResultType] =this.copy(m);//new Query(m)
      queryRes.where = this.generador.crearWhereSQL(f(m))
      if (this.selectFrom == ""){
        queryRes.selectFrom = this.generador.crearSelecSimpleSql(m);
      }
      println(queryRes.where);
      return queryRes
      
      }
 
    
    def sql() : String = this.generador.generarCodigoSql(this)
    def run(value : ResultType) :Seq[Any] ={ 
      return this.ejecutarBD(this.sql, value)
    
  }
    
    def ejecutarBD(sql : String,value : ResultType) :Seq[Any] = {
      var connection :Connection =DriverManager.getConnection( "jdbc:h2:~/"+m.nombreT);
      val SqlQuery :String = sql
      var rs :ResultSet = null;
      var res : Seq[Any]= null;
    try {
   connection.setAutoCommit(false);
   var selectPreparedStatement: PreparedStatement = null;
   selectPreparedStatement = connection.prepareStatement(SqlQuery);
    rs = selectPreparedStatement.executeQuery();
    /*while (rs.next()) {
          System.out.println("Edad "+rs.getInt("edad")+" Nombre "+rs.getString("nombre"));
         }*/
   res = this.machearSegunTipo(value, rs);
   selectPreparedStatement.close();
   connection.commit();
   return res;
   
    }catch  {
     case e: SQLException => {System.out.println("Exception Message " + e.getLocalizedMessage()); return res}
     case e2 : Exception =>  {e2.printStackTrace(); return res}
        } finally {
            connection.close();
        }
    }
    def machearSegunTipo[S](value :S, rs : ResultSet): Seq[Any]={
      var seq:Seq[Any]  = Seq[ResultType]()
      
      value match {
      case v: String => while (rs.next()) {
                          val str :String = rs.getString("nombre")
                          System.out.println(" Nombre "+str);
                           seq = seq.:+(str)
                        }  
      case a: Int => while (rs.next()) {
                          val str :Int = rs.getInt("edad")
                          System.out.println("Edad "+str);
                          seq = seq.+:(str)
                        }
      case p : Perro =>  while (rs.next()) {
                          val strI :Int = rs.getInt("edad")
                          val strS :String = rs.getString("nombre")
                          System.out.println("Edad "+strI+" Nombre "+strS);
                          val perro : Perro = new Perro(strS,strI);
                          seq = seq.+:(perro)
                        } 
    }
      return seq
    }
    
    def add(o: m.ResultType) : Unit = {
      println(generador.generarInsert(m.nombreT, m.toColumnValues(o)))
      var connection :Connection =DriverManager.getConnection( "jdbc:h2:~/"+m.nombreT);
      val creatInsert :String = generador.generarInsert(m.nombreT, m.toColumnValues(o))
    
    try {
   connection.setAutoCommit(false);
   
   var insertPreparedStatement: PreparedStatement = null;
   
   insertPreparedStatement = connection.prepareStatement(creatInsert);
   insertPreparedStatement.executeUpdate();
   insertPreparedStatement.close();
   
   connection.commit();
  }catch  {
     case e: SQLException => System.out.println("Exception Message " + e.getLocalizedMessage());
     case e2 : Exception =>  e2.printStackTrace();
        } finally {
            connection.close();
        }  
    
    }
    
    def ++ [T<: Table[ResultType],ResultType](q : Query[T,ResultType]): Query[Table[m.ResultType],m.ResultType]={
      val queryRes : Query[Table[m.ResultType],m.ResultType] =this.copy(m);
      queryRes.unionSql = this.generador.crearSqlUnion(this.sql(),q.sql())
      return queryRes
    }
    def update[TipoColumna,ValorAEvaluar](f: m.MappingType => Condition[TipoColumna,ValorAEvaluar], newValue: TipoColumna) : Unit = ???
       
}  
  

  /* 
  
  def filter(s: String): Query[T] = {
    
    if(this.select == ""){
    this.select = "select * " + "from " + tabla.nombreT }     
    val q = this
    q.stringSql = this.select + q.stringSql + " where " + s
    return q
  }
  
  
  def map[R](f : T => Column[R]): Query[R] ={
    
    this.select = "select " + col.nombreC + "from " + this.tabla.nombreT
    val q = this
    q.stringSql = this.select 
    return q
  }
  
  
  def sql() : String = ???
  
  def run() : Seq[ResultType] = ???
  
  
  def add(obj: Object) ={
    
  }
*/  