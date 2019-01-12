import org.junit.Test
import org.junit.Assert._
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import org.h2.tools.DeleteDbFiles;

class TestH2 {
  
  
  val perros = Perros;
  val q = new Query[Perros.MappingType,Perros.ResultType](perros)
  val DB_DRIVER:String = "org.h2.Driver";
  val DB_CONNECTION = "jdbc:h2:~/test";
  val DB_USER = "";
  val DB_PASSWORD = "";
  
 /* @Test
  def testH2Connection(): Unit = {
    try {     
   DeleteDbFiles.execute("~", "test", true);    
   }catch {
           case e :SQLException => e.printStackTrace();
        }
   var connection :Connection =DriverManager.getConnection( "jdbc:h2:~/test");
   var  CreateQuery:String = "CREATE TABLE PERSONAS(id int primary key, name varchar(255))";
   var  InsertQuery:String = "INSERT INTO PERSONAS" + "(id, name) values" + "(?,?)";
   var  SelectQuery:String = "select * from PERSONAS";
   try {
   connection.setAutoCommit(false);
   
   var createPreparedStatement: PreparedStatement = null;
   var insertPreparedStatement: PreparedStatement = null;
   var selectPreparedStatement: PreparedStatement = null;
   
   createPreparedStatement = connection.prepareStatement(CreateQuery);
   createPreparedStatement.executeUpdate();
   createPreparedStatement.close();
   
   insertPreparedStatement = connection.prepareStatement(InsertQuery);
   insertPreparedStatement.setInt(1, 1);
   insertPreparedStatement.setString(2, "Jose");
   insertPreparedStatement.executeUpdate();
   insertPreparedStatement.setInt(1, 2);
   insertPreparedStatement.setString(2, "Pedro");
   insertPreparedStatement.executeUpdate();
   insertPreparedStatement.close();
   
   selectPreparedStatement = connection.prepareStatement(SelectQuery);
   var rs: ResultSet = selectPreparedStatement.executeQuery();
   System.out.println("H2 Database  PreparedStatement");
   while (rs.next()) {
          System.out.println("Id "+rs.getInt("id")+" Name "+rs.getString("name"));
         }
   selectPreparedStatement.close();
   connection.commit();
   }catch  {
     case e: SQLException => System.out.println("Exception Message " + e.getLocalizedMessage());
     case e2 : Exception =>  e2.printStackTrace();
        } finally {
            connection.close();
        }
    
  }*/
  
  @Test
  def testCrearTablaDePerrosYAgregarPerros():Unit ={
    perros.crearTabla();
    q.add(Perro("pepe", 25))
    q.add(Perro("Pedrito", 35))
    q.add(Perro("Firulais", 12))
    //var query1 = q.map(_.nombre).filter(_.nombre === "Pepe") NO FUNCIONA, HABRA QUE PREGUNTAR 
    // POR LO QUE VI PARECE QUE ES POR YA NO TIENE LA TABLA DE PERROS O ALGO Asi
    var query2 = q.map(_.nombre)
    var query3 = q.filter(_.nombre === "pepe")
    var query4 = q.map(_.edad)
    var query5 = q.sortby(_.edad.desc())
    println(query2.sql())
    println(query3.sql())
    println(query5.sql())
    var perrosPepe = query3.run(new Perro ("",0))
    var mombresPerros = query2.run("")
    var edadPerros = query4.run(0)
    var perrosDesc = query5.run(new Perro ("",0))
    println(perrosPepe.head)
    
  }
  
  @Test
  def testVerificarFilterMapLDropTakeYStringSqlDePerros(): Unit = {
    var query = q.filter(_.edad < 10)
    var query1 = q.filter(_.edad > 30)
    //var query1 = q.filter(_.nombre === "hola").map(_.edad > 20)
    var query2 = q.map(_.edad);
    var query3 = q.sortby(_.nombre.desc)
    var query4= q.take(2)
    var query5 = q.drop(2)
    var query6 = q.drop(1).take(1)
    println(query4.sql())
    println(query5.sql())
    println(query6.sql())
    var queryUnion = query ++ query1
    println(queryUnion.sql())
    
    
  }
  

  @Test
  def testCrearUnaTablaDePerros(): Unit = {
    val resSql: String = perros.generarSQL
    println(resSql)
    
   // q.run()
    q.map(_.edad)
 }
  
}