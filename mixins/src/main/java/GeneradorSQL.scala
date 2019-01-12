

class GeneradorSQL {
  
  def generarCodigoSql[T<: Table[R],R](q: Query[T,R]) : String ={
    val sqlString :String = s" ${q.selectFrom} ${q.where} ${q.order} ${q.limit} ${q.offset} ${q.unionSql}"//q.selectFrom + q.where +q.order+ q.limit +q.offset
    return sqlString;
    
  }
  def crearSQLLimit( tabla: Table[_], i :Int) :String ={
    val sqlString :String = s"Limit ${i}"
    return sqlString
  }
  
  def crearSQLOfset(tabla: Table[_], i :Int):String = {
     val sqlString :String = s"offset  ${i}"
    return sqlString
  }
  
  def crearWhereSQL[TipoColumna,VlorAEvaluar](c:Condition[TipoColumna,VlorAEvaluar]):String = {
    val createQueryWhere :String = c.generarSQL();
    return createQueryWhere;
  }
  def crearTablaSQL(table: Table[_]) : String =  {
    var createQuery:String = s" CREATE TABLE ${table.nombreT} (${table.*().map(_.sql()).mkString(", ")}) ";
    return createQuery
  }
  
  def insertIntoSQL(nombreTabla: String, columna: (StringColumn,IntColumn), perro: Perro)  : String = {
    
    var insertQuery: String = " INSERT INTO " + nombreTabla + "(" + columna._1.unNombreC + ", " + columna._2.unNombreC + ")" + "values" + "(" + perro.nombreP + "," + perro.edad + ") "    
    return insertQuery
  }

  def generarInsert(nombreTabla: String, columnValues: Seq[(Column[_],Any)]) = {
    s"INSERT INTO $nombreTabla (${columnValues.map(_._1.nombreColumna).mkString(", ")}) " + 
    s"values (${columnValues.map(cv => toSqlPrimitive(cv._2)).mkString(", ")})"
   // s"values (?,?)"
  }

  def toSqlPrimitive(value: Any): String = {
    value match {
      case v: String => "'" +value+"'" //s""""${value}""""
      case a => a.toString() 
    }
  }
  
  def crearSelectSQL[S](columna: Column[S], tabla: Table[_]): String = {
    
     s"SELECT ${columna.nombreColumna} FROM ${tabla.nombreT} " 
  }
  
  def crearSelecSimpleSql(tabla: Table[_]):String ={
    s"SELECT * FROM ${tabla.nombreT} ";
  }
  
  def crearSqlUnion(q : String,q2 : String) :String ={
    val sqlString = s"${q} union all ${q2}"
    return sqlString
  }
}