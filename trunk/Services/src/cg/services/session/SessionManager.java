package cg.services.session;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cg.services.session.api.ISessionKey;

//the thread which handling the requests maybe different for a http session
//use sessionId to make sure using the correct session
public class SessionManager
{
  //use Long instead of ISessionKey as the key to increase the performance
  //the value in the ThreadLocal must be remove at the same thread.
  //but the session maybe removed by other thread, so, don't use ThreadLocal
  //  private static ThreadLocal< Map< Long, Object > > threadSessions = new ThreadLocal< Map< Long, Object > >();
  
  //sessionId ==> session attributes
  private static Map< String, Map< Long, Object > > sessionIdAttrbutesMap = new ConcurrentHashMap< String, Map< Long, Object > >();
  
  // thread id ==> session id
  private static Map< Long, String > threadSessionIdMap = new ConcurrentHashMap< Long, String >();
  
  //generate a session-id and put into the session
  public synchronized static String startSession()
  {
    String sessionId = generateSessionId();
    Map< Long, Object > attributes = new HashMap< Long, Object >();
    threadSessionIdMap.put( getCurrentThreadId(), sessionId );
    sessionIdAttrbutesMap.put( sessionId, attributes );
    return sessionId;
  }
  
  public synchronized static void endSession()
  {
    long threadId = getCurrentThreadId();
    String sessionId = threadSessionIdMap.get( threadId );
    if( sessionId != null )
    {
      sessionIdAttrbutesMap.remove( sessionId );
    }
    threadSessionIdMap.remove( threadId );
  }
  
  //this method is called when receive a new request;
  //make sure the data get from ThreadLocal is corresponding to the sessionId
  //this method is used in case the thread which handles the different requests of same session changed.
  public static void adjust( String sessionId )
  {
    Long threadId = getCurrentThreadId();
    if( sessionId.equals( threadSessionIdMap.get( threadId ) ) )
      return;
    
    //there should have another threadId which mapped to this sessionId, we should clean it
    Long leftOverThreadId = null;
    for( Map.Entry< Long, String > entry : threadSessionIdMap.entrySet() )
    {
      if( sessionId.equals( entry.getValue() ) )
        leftOverThreadId = entry.getKey();
    }
    if( leftOverThreadId != null )
      threadSessionIdMap.remove( leftOverThreadId );

    threadSessionIdMap.put( threadId, sessionId );
    //the attributes which related to this sessionId suppose has been created.
  }
  
  private static Map< Long, Object > getAttributes()
  {
    String sessionId = threadSessionIdMap.get( getCurrentThreadId() );
    if( sessionId == null )
    {
      // the session hasn't created or the session time-out ( thread shutdown ) and a new thread handling 
      throw new RuntimeException( "sessionId should not be null. please call startSession() before using session." );
    }

    return sessionIdAttrbutesMap.get( sessionId );
  }
  
  public static void putAttribute( ISessionKey key, Object value )
  {
    getAttributes().put( SessionKeyManager.getId( key ), value );
  }
  
  public static Object getAttribute( ISessionKey key )
  {
    return getAttributes().get( SessionKeyManager.getId( key ) );
  }
  
  protected static String generateSessionId()
  {
    return "SI-" + Calendar.getInstance().getTimeInMillis() + "-" + getCurrentThreadId();
  }
  
  protected static long getCurrentThreadId()
  {
    return Thread.currentThread().getId();
  }
}
