package cg.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

// one ConnectionManager manages the connection to one url;
public class ConnectionManager
{
  private ConnectionParameters connectionParameters;
  private List< Connection > connections = new ArrayList< Connection >(); 
  public ConnectionManager( ConnectionParameters connectionParameters )
  {
    setConnectionParameters( connectionParameters );
  }
  
  public void setConnectionParameters( ConnectionParameters connectionParameters )
  {
    this.connectionParameters = connectionParameters;
  }
  
  public Connection newConnection()
  {
    Properties props = new Properties(); // connection properties
    props.put( "user", connectionParameters.getUserName() );
    props.put( "password", connectionParameters.getPassword() );

    try
    {
      Connection connection = DriverManager.getConnection( connectionParameters.getUrl(), props );
      connections.add( connection );
      return connection;
    }
    catch ( Exception e )
    {
      e.printStackTrace();
    }
    return null;
  }
}
