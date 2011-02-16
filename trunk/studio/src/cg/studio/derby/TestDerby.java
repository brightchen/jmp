package cg.studio.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestDerby
{
  public static final String derbyEmbededDriver = "org.apache.derby.jdbc.EmbeddedDriver";

  // derby connection: jdbc:derby:[subsubprotocol:][databaseName][;attribute=value]*
  public static final String derbyDatabase      = "user1";
  public static final String derbyProtocol      = "jdbc:derby:";
  public static final String derbyConnection    = derbyProtocol + derbyDatabase;

  public static void main( String argv[] )
  {
    Properties props = new Properties(); // connection properties
    // providing a user name and password is optional in the embedded
    // and derbyclient frameworks
    props.put( "user", "user1" );
    props.put( "password", "user1" );

    try
    {
      Connection conn = DriverManager.getConnection( derbyProtocol + derbyDatabase + ";create=true", props );

      // We want to control transactions manually. Autocommit is on by
      // default in JDBC.
      conn.setAutoCommit( false );

      List< Statement > statements = new ArrayList< Statement >();

      {
        // check userTable existence
        Statement s = conn.createStatement();
        try
        {
          ResultSet rs = s.executeQuery( "SELECT count(*) from userTable" );
        }
        catch( SQLException e )
        {
          // Creating a statement object that we can use for running various SQL statements commands against the database.
          s = conn.createStatement();
          s.execute( "create table userTable( userName varchar(40), firstName varchar(40), lastName varchar(40), password varchar(40) )" );
          statements.add( s );
        }
      }

      {
        
        PreparedStatement ps = conn.prepareStatement( "insert into userTable values (?, ?, ?, ?)" );

        ps.setString( 1, "user1" );
        ps.setString( 2, "first" );
        ps.setString( 3, "user" );
        ps.setString( 4, "user1" );
        ps.executeUpdate();

        statements.add( ps );
      }
      
      conn.commit();
      
      //query
      {
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery( "SELECT * from userTable" );
        while( rs.next() )
        {
          for( int index = 1; index <= 4; ++index )
          {
            String data = rs.getString( index );
            System.out.println( "column " + index + ": " + data );
          }
        }
      }

    }
    catch ( Exception e )
    {
      e.printStackTrace();
    }
  }

  public static void shutdownDerby()
  {
    try
    {
      DriverManager.getConnection( "jdbc:derby:;shutdown=true" );
    }
    catch ( Exception e )
    {
      e.printStackTrace();
    }
  }
}
