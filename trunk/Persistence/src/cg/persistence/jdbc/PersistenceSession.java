package cg.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cg.common.identity.GlobalLongIdentityGenerator;
import cg.common.identity.Identifiable;
import cg.persistence.api.PersistenceException;


public class PersistenceSession implements Identifiable< Long >
{
  private ConnectionParameters connectionParameters;
  private Connection connection;
  private long identity;
  
  //not allow outside construct this class
  protected PersistenceSession( ConnectionParameters connectionParameters )
  {
    identity = GlobalLongIdentityGenerator.generateIdentity();
    setConnectionParameters( connectionParameters );
  }
  
  //return the identity of session
  public static long connect( ConnectionParameters connectionParameters ) throws PersistenceException
  {
    PersistenceSession session = new PersistenceSession( connectionParameters );
    return session.connect();
  }
  
  protected long connect() throws PersistenceException
  {
    ConnectionManager connectionManager = ConnectionManagerFactory.getConnectionManager( connectionParameters );
    connection = connectionManager.newConnection();
    return identity;
  }
  
  public void setConnectionParameters( ConnectionParameters connectionParameters )
  {
    this.connectionParameters = connectionParameters;
  }
  
  public Map< String, List< Object > > executeNativeQuery( String sql ) throws PersistenceException
  {
    return executeNativeQuery( sql, null );
  }
  
  public Map< String, List< Object > > executeNativeQuery( String sql, Map< Integer, Object > params ) throws PersistenceException
  {
    return convertResultSetToMap( fetchResultSet( sql, params ) );
  }
  
  protected ResultSet fetchResultSet( String sql, Map< Integer, Object > params ) throws PersistenceException
  {
    if( connection == null )
      connect();
    
    if( connection == null )  //throw exception here
      return null;

    try
    {
      PreparedStatement ps = connection.prepareStatement( sql );
      if( params != null && params.size() > 0 )
      {
        for( Map.Entry< Integer, Object > entry : params.entrySet() )
        {
          ps.setObject( entry.getKey(), entry.getValue() );
        }
      }
      
      ResultSet rs = ps.executeQuery();
      ps.close();
      return rs;
    }
    catch( SQLException e )
    {
      throw new PersistenceException( "fetchResultSet() failed.", e );
    }
  }
  
  protected Map< String, List< Object > > convertResultSetToMap( ResultSet rs ) throws PersistenceException
  {
    Map< String, List< Object > > nameColumnData = new HashMap< String, List< Object > >();

    try
    {
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();

    while( rs.next() )
    {
      //column index begin with 1
      for( int columnIndex = 1; columnIndex <= columnCount; ++columnIndex )
      {
        String columnName = metaData.getColumnName( columnIndex );
        List< Object > dataList = nameColumnData.get( columnName );
        if( dataList == null )
        {
          dataList = new ArrayList< Object >();
          nameColumnData.put( columnName, dataList );
        }
        dataList.add( rs.getObject( columnIndex ) );
      }
    }
    
    return nameColumnData;
    }
    catch( SQLException e )
    {
      throw new PersistenceException( "convertResultSetToMap()", e );
    }
  }
  
  public boolean executeUpdate( String sql ) throws PersistenceException
  {
    if( connection == null )
      connect();
    
    if( connection == null ) 
      throw new PersistenceException( "Can NOT connect to database." );

    try
    {
    Statement statement = connection.createStatement();
    return statement.execute( sql );
    }
    catch( SQLException e )
    {
      throw new PersistenceException( "executeUpdate() failed", e );
    }
  }

  public void close()
  {
    if( connection == null )
      return;
    try
    {
      connection.close();
    }
    catch( SQLException e )
    {
    }
  }

  public static void close( PersistenceSession session )
  {
    session.close();
  }
  @Override
  public Long getIdentity()
  {
    return identity;
  }
}
