package cg.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cg.common.identity.GlobalLongIdentityGenerator;
import cg.common.identity.Identifiable;
import cg.persistence.api.PersistenceException;
import cg.persistence.model.ColumnInfo;
import cg.persistence.model.ColumnMetaData;

public class PersistenceSession implements Identifiable< Long >
{
  private ConnectionParameters connectionParameters;
  private Connection           connection;
  private long                 identity;

  // not allow outside construct this class
  protected PersistenceSession( ConnectionParameters connectionParameters )
  {
    identity = GlobalLongIdentityGenerator.generateIdentity();
    setConnectionParameters( connectionParameters );
  }

  // return the identity of session
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

  protected void ensureConnected() throws PersistenceException
  {
    if ( connection == null )
      connect();
  }

  public void setConnectionParameters( ConnectionParameters connectionParameters )
  {
    this.connectionParameters = connectionParameters;
  }

  public int executeNativeUpdate( String sql ) throws PersistenceException
  {
    ensureConnected();

    try
    {
      Statement statement = connection.createStatement();

      int rows = statement.executeUpdate( sql ); // should judge the sql is query or update/create etc
      return rows;
    }
    catch ( SQLException e )
    {
      throw new PersistenceException( "fetchResultSet() failed.", e );
    }

  }

  public List< ColumnInfo > executeNativeQuery( String sql ) throws PersistenceException
  {
    return executeNativeQuery( sql, null );
  }

  public List< ColumnInfo > executeNativeQuery( String sql, Map< Integer, Object > params )
      throws PersistenceException
  {
    ResultSet rs = fetchResultSet( sql, params );
    List< ColumnInfo > columns = convertOutput( rs );

    try
    {
      //close the result set here
      rs.close();
    }
    catch( SQLException e )
    {
    }
    return columns;
  }

  protected ResultSet fetchResultSet( String sql, Map< Integer, Object > params ) throws PersistenceException
  {
    ensureConnected();

    try
    {
      PreparedStatement ps = connection.prepareStatement( sql );
      if ( params != null && params.size() > 0 )
      {
        for ( Map.Entry< Integer, Object > entry : params.entrySet() )
        {
          ps.setObject( entry.getKey(), entry.getValue() );
        }
      }

      ResultSet rs = ps.executeQuery(); // should judge the sql is query or update/create etc
      return rs;
    }
    catch ( SQLException e )
    {
      throw new PersistenceException( "fetchResultSet() failed.", e );
    }
  }

  // return the map ( columnName ==> column data list );
  protected List< ColumnInfo > convertOutput( ResultSet rs ) throws PersistenceException
  {
    try
    {
      List< ColumnInfo > columnInfos = new ArrayList< ColumnInfo >();
      ResultSetMetaData metaData = rs.getMetaData();
      int columnCount = metaData.getColumnCount();

      boolean firstRow = true;
      while ( rs.next() )
      {
        // column index begin with 1
        for ( int columnIndex = 1; columnIndex <= columnCount; ++columnIndex )
        {
          ColumnInfo columnInfo = null;
          if ( firstRow )
          {
            ColumnMetaData cmd = new ColumnMetaData( metaData, columnIndex );

            // create column info;
            columnInfo = new ColumnInfo();
            columnInfo.setMetaData( cmd );

            // add to list;
            columnInfos.add( columnInfo );
          }
          else
          {
            columnInfo = columnInfos.get( columnIndex - 1 );
          }

          // add data into columnInfo
          columnInfo.addObject( rs.getObject( columnIndex ) );
        }
        firstRow = false;
      }

      return columnInfos;
    }
    catch ( SQLException e )
    {
      throw new PersistenceException( "convertOutput()", e );
    }
  }

  public boolean executeUpdate( String sql ) throws PersistenceException
  {
    if ( connection == null )
      connect();

    if ( connection == null )
      throw new PersistenceException( "Can NOT connect to database." );

    try
    {
      Statement statement = connection.createStatement();
      return statement.execute( sql );
    }
    catch ( SQLException e )
    {
      throw new PersistenceException( "executeUpdate() failed", e );
    }
  }

  public void close()
  {
    if ( connection == null )
      return;
    try
    {
      connection.close();
    }
    catch ( SQLException e )
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
