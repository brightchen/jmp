package cg.services.session;

import java.util.Calendar;
import java.util.Map;

//the thread which handling the requests maybe different for a http session
//use sessionId to make sure using the correct session
public class SessionManager
{
  private static ThreadLocal< Map< SessionKey, Object > > session = new ThreadLocal< Map< SessionKey, Object > >();
  
  //generate a session-id and put into the session
  public static void startSession()
  {
    String sessionId = generateSessionId();
    putAttribute( SessionKey.sessionId, sessionId );
  }
  
  public static void endSession( String sessionId )
  {
    
  }
  
  //this method is called when receive a new request;
  //make sure the data get from ThreadLocal is corresponding to the sessionId
  public static void adjust( String sessionId )
  {
    
  }
  
  
  public static void putAttribute( SessionKey key, Object value )
  {
    session.get().put( key, value );
  }
  
  public static void getAttribute( SessionKey key )
  {
    session.get().get( key );
  }
  
  protected static String generateSessionId()
  {
    return "SI-" + Calendar.getInstance().getTimeInMillis() + "-" + Thread.currentThread().getId();
  }
}
