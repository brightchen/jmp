package cg.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
  
  public PersistenceSession( ConnectionParameters connectionParameters )
  {
    identity = GlobalLongIdentityGenerator.generateIdentity();
    setConnectionParameters( connectionParameters );
  }
  
  public void connect() throws PersistenceException
  {
    ConnectionManager connectionManager = ConnectionManagerFactory.getConnectionManager( connectionParameters );
    connection = connectionManager.newConnection();
  }
  
  public void setConnectionParameters( ConnectionParameters connectionParameters )
  {
    this.connectionParameters = connectionParameters;
  }
  
  public Map< String, List< Object > > executeNativeQuery( String sql, Map< Integer, Object > params ) throws Exception
  {
    return convertResultSetToMap( fetchResultSet( sql, params ) );
  }
  
  protected ResultSet fetchResultSet( String sql, Map< Integer, Object > params ) throws Exception
  {
    if( connection == null )
      connect();
    
    if( connection == null )  //throw exception here
      return null;

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
  
  protected Map< String, List< Object > > convertResultSetToMap( ResultSet rs ) throws Exception
  {
    Map< String, List< Object > > nameColumnData = new HashMap< String, List< Object > >();

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
  
  public boolean executeUpdate( String sql ) throws Exception
  {
    if( connection == null )
      connect();
    
    if( connection == null )  //throw exception here
      return false;

    Statement statement = connection.createStatement();
    return statement.execute( sql );
  }


  @Override
  public Long getIdentity()
  {
    return identity;
  }
}
