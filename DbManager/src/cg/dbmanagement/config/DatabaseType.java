package cg.dbmanagement.config;

import java.util.HashMap;
import java.util.Map;

public class DatabaseType
{
  public static enum TYPE
  {
    HSQL,
    ORACLE,
    DERBY
  }
  
  public static Map< TYPE, String > DatabaseTypeDrivers = new HashMap< TYPE, String >();
  static
  {
    DatabaseTypeDrivers.put( TYPE.HSQL, "org.hsqldb.jdbcDriver" );    //jdbcDriver is class
    DatabaseTypeDrivers.put( TYPE.ORACLE, "oracle.jdbc.driver.OracleDriver" );
    DatabaseTypeDrivers.put( TYPE.DERBY, "org.apache.derby.jdbc.EmbeddedDriver" );
  }
  
  public static String getDriverClass( TYPE type )
  {
    return DatabaseTypeDrivers.get( type );
  }
  
  public static String getDriverClass( String databaseTypeName )
  {
    TYPE type = getDatabaseType( databaseTypeName );
    if( type == null )
      return null;
    return getDriverClass( type );
  }
  
  public static TYPE getDatabaseType( String databaseTypeName )
  {
    for( TYPE type : TYPE.values() )
    {
      if( type.name().equalsIgnoreCase( databaseTypeName ) )
      {
        return type;
      }
    }
    return null;
  }
}
