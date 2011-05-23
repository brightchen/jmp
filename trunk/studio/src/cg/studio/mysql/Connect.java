package cg.studio.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Connect
{
  private static final String driver = "com.mysql.jdbc.Driver";

  private String              url    = "jdbc:mysql://localhost:3306/";
  private String              db     = "userdb";
  private String              user   = "user1";
  private String              pass   = "user1";

  public static void main( String[] args )
  {
    Connect connect = new Connect();
    String[] tableNames = connect.getTableNames();
    if( tableNames == null )
    {
      System.out.println( "no table found." );
      return;
    }
    for( String tableName : tableNames )
    {
      System.out.println( tableName );
    }
  }

  public Connection getConnection()
  {
    try
    {
      Connection con = null;
      Class.forName( driver );
      con = DriverManager.getConnection( url + db, user, pass );
      return con;
    }
    catch ( Exception e )
    {
      e.printStackTrace();
      return null;
    }
  }

  public String[] getTableNames()
  {
    try
    {
      Connection con = getConnection();
      if( con == null )
      {
        System.out.println( "Can't get connection." );
        return null;
      }
      DatabaseMetaData dbm = con.getMetaData();
      String[] types =
      { "TABLE" };
      ResultSet rs = dbm.getTables( null, null, "%", types );

      Vector< String > tableNames = new Vector< String >();
      while ( rs.next() )
      {
        String table = rs.getString( "TABLE_NAME" );
        tableNames.add( table );
      }
      con.close();

      return tableNames.size() == 0 ? null : tableNames.toArray( new String[tableNames.size()] );
    }
    catch ( SQLException e )
    {
      e.printStackTrace();
      return null;
    }

  }
}