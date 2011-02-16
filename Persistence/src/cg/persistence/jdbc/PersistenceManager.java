package cg.persistence.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cg.persistence.api.PersistenceException;

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
  
  public int executeNativeUpdate( String sessionId, String sql ) throws PersistenceException
  {
    PersistenceSession session = getSessionById( sessionId );
    return executeNativeUpdate( session, sql );
  }
  
  public int executeNativeUpdate( PersistenceSession session, String sql ) throws PersistenceException
  {
    return session.executeNativeUpdate( sql );
  }
  
  //this method provides a shortcut to execute a native sql query.
  //it connect to database, execute sql and then release the connection.
  public static Map< String, List< Object > > executeNativeQuery( ConnectionParameters connectionParameters, 
                                                                  String sql ) throws PersistenceException
  {
    return executeNativeQuery( connectionParameters, sql, null );
  }
  
  public static Map< String, List< Object > > executeNativeQuery( ConnectionParameters connectionParameters, 
                                                                  String sql, Map< Integer, Object > queryParams ) throws PersistenceException
  {
    PersistenceSession session = new PersistenceSession( connectionParameters );
    session.connect();
    Map< String, List< Object > > result = executeNativeQuery( session, sql, queryParams );
    session.close();
    return result;
  }

  public static Map< String, List< Object > > executeNativeQuery( String sessionId, String sql ) throws PersistenceException
  {
    return executeNativeQuery( sessionId, sql, null );
  }

  public static Map< String, List< Object > > executeNativeQuery( String sessionId, 
                                                                  String sql, Map< Integer, Object > queryParams ) throws PersistenceException
  {
    PersistenceSession session = getSessionById( sessionId );
    return executeNativeQuery( session, sql, queryParams );
  }
  
  protected static Map< String, List< Object > > executeNativeQuery( PersistenceSession session, 
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
