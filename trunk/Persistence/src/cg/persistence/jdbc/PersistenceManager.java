package cg.persistence.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cg.persistence.api.PersistenceException;
import cg.persistence.model.ColumnInfo;
import cg.persistence.model.SqlOutput;

// this class is a facade of the persistence layer
public class PersistenceManager
{
  private static Map< Long, PersistenceSession > sessionMap = new HashMap< Long, PersistenceSession >();
  
  // return the identity of the persistence session
  public static String connect( ConnectionParameters connectionParameters ) throws PersistenceException
  {
    PersistenceSession session = new PersistenceSession( connectionParameters );
    long sessionId = session.connect();
    sessionMap.put( sessionId, session );
    return String.valueOf( sessionId );
  }
  
  public static SqlOutput executeNativeSql( String sessionId, String sql ) throws PersistenceException
  {
    PersistenceSession session = getSessionById( sessionId );
    return executeNativeSql( session, sql );
  }
  
  protected static boolean isQuerySql( String sql )
  {
    final String select = "select";
    sql = sql.trim();
    return ( sql.length() > select.length() && sql.startsWith( select ) );
  }
  
  public static SqlOutput executeNativeSql( PersistenceSession session, String sql ) throws PersistenceException
  {
    if( isQuerySql( sql ) )
    {
      List< ColumnInfo > columns = executeNativeQuery( session, sql, null );
    }
    else
    {
      int result = executeNativeUpdate( session, sql );
    }
    return null;
  }
  
  public static int executeNativeUpdate( String sessionId, String sql ) throws PersistenceException
  {
    PersistenceSession session = getSessionById( sessionId );
    return executeNativeUpdate( session, sql );
  }
  
  public static int executeNativeUpdate( PersistenceSession session, String sql ) throws PersistenceException
  {
    return session.executeNativeUpdate( sql );
  }
  
  //this method provides a shortcut to execute a native sql query.
  //it connect to database, execute sql and then release the connection.
  public static List< ColumnInfo > executeNativeQuery( ConnectionParameters connectionParameters, 
                                                                  String sql ) throws PersistenceException
  {
    return executeNativeQuery( connectionParameters, sql, null );
  }
  
  public static List< ColumnInfo > executeNativeQuery( ConnectionParameters connectionParameters, 
                                                                  String sql, Map< Integer, Object > queryParams ) throws PersistenceException
  {
    PersistenceSession session = new PersistenceSession( connectionParameters );
    session.connect();
    List< ColumnInfo > result = executeNativeQuery( session, sql, queryParams );
    session.close();
    return result;
  }

  public static List< ColumnInfo > executeNativeQuery( String sessionId, String sql ) throws PersistenceException
  {
    return executeNativeQuery( sessionId, sql, null );
  }

  public static List< ColumnInfo > executeNativeQuery( String sessionId, 
                                                                  String sql, Map< Integer, Object > queryParams ) throws PersistenceException
  {
    PersistenceSession session = getSessionById( sessionId );
    return executeNativeQuery( session, sql, queryParams );
  }
  
  protected static List< ColumnInfo > executeNativeQuery( PersistenceSession session, 
                                                                  String sql, Map< Integer, Object > queryParams ) throws PersistenceException
  {
    return session.executeNativeQuery( sql, queryParams );
  }

  protected static PersistenceSession getSessionById( String sessionId )
  {
    Long lSessionId = null;
    try
    {
      lSessionId = Long.parseLong( sessionId );
    }
    catch( NumberFormatException e )
    {
      throw new RuntimeException( "Invalid SessionId: " + sessionId );
    }
    
    PersistenceSession session = sessionMap.get( lSessionId );
    if( session == null )
      throw new RuntimeException( "Invalid SessionId: " + sessionId + "; Can NOT found the session." );
    return session;
  }
}
