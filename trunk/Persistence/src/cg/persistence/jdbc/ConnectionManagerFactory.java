package cg.persistence.jdbc;

import java.util.HashMap;
import java.util.Map;

public class ConnectionManagerFactory
{
  private static Map< ConnectionParameters, ConnectionManager > connectionManagerMap = new HashMap< ConnectionParameters, ConnectionManager >();

  public static ConnectionManager getConnectionManager( ConnectionParameters params )
  {
    ConnectionManager connectionManager = connectionManagerMap.get( params );
    if( connectionManager != null )
      return connectionManager;
    
    connectionManager = new ConnectionManager( params );
    connectionManagerMap.put( params, connectionManager );
    return connectionManager;
  }
}
