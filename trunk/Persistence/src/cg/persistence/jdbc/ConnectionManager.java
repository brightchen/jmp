package cg.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

// one ConnectionManager manages the connection to one url;
public class ConnectionManager
{
  private ConnectionParameters connectionParameters;

  private Object               idleReading    = new Object();
  private Object               idleWriting    = new Object();
  private Object               workingReading = new Object();
  private Object               workingWriting = new Object();

  private List< Connection >   idleConnections    = new LinkedList< Connection >();
  private List< Connection >   workingConnections = new LinkedList< Connection >();

  public ConnectionManager( ConnectionParameters connectionParameters )
  {
    setConnectionParameters( connectionParameters );
  }

  public void setConnectionParameters( ConnectionParameters connectionParameters )
  {
    this.connectionParameters = connectionParameters;
  }

  // get an ideal connection for use.
  public Connection getConnectionForUse()
  {
    Connection connection = null;
    if ( idleConnections.size() > 0 )
    {
      synchronized ( idleWriting )
      {
        if ( idleConnections.size() > 0 )
        {
          synchronized ( idleReading )
          {
            connection = idleConnections.remove( 0 );
          }
        }
      }
    }

    if ( connection != null )
    {
      // add this connection into working list
      synchronized ( workingWriting )
      {
        synchronized ( workingReading )
        {
          workingConnections.add( connection );
        }
      }
      return connection;
    }

    // can't get connection from ideal list, create a new connection
    return newConnection();
  }

  // this connection is not using by client any more, put it as idle
  // move connection from working to ideal
  public void returnConnection( Connection connection )
  {
    // remove from the working list
    synchronized ( workingWriting )
    {
      synchronized ( workingReading )
      {
        workingConnections.remove( connection );
      }
    }

    // add this connection into idle list
    synchronized ( idleWriting )
    {
      synchronized ( idleReading )
      {
        idleConnections.add( connection );
      }
    }
  }

  protected Connection newConnection()
  {
    Properties props = new Properties(); // connection properties
    props.put( "user", connectionParameters.getUserName() );
    props.put( "password", connectionParameters.getPassword() );

    try
    {
      Connection connection = DriverManager.getConnection( connectionParameters.getUrl(), props );
      synchronized ( workingWriting )
      {
        synchronized ( workingReading )
        {
          workingConnections.add( connection );
        }
      }
      return connection;
    }
    catch ( Exception e )
    {
      e.printStackTrace();
    }
    return null;
  }
  
  //the connection suppose to be detached from list
  protected void closeConnection( Connection connection )
  {
    try
    {
      connection.close();
    }
    catch( Exception e)
    {
      e.printStackTrace();
    }
  }
}
