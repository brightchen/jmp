package cg.services.session;

import java.util.HashMap;
import java.util.Map;

import cg.services.session.api.ISessionKey;

public class SessionKeyManager
{
  private static Map< String, Long > categoryStartIdMap = new HashMap< String, Long >();
  private static long nextStartId = 0;
  
  public static long getId( ISessionKey sessionKey )
  {
    long startId = getStartId( sessionKey );
    return startId + sessionKey.ordinal();
  }
  
  public static String getName( ISessionKey sessionKey )
  {
    return sessionKey.getClass().getName() + "-" + sessionKey.name();
  }
  
  protected static long getStartId( ISessionKey sessionKeyEnum )
  {
    String className = sessionKeyEnum.getClass().getName();
    Long startId = categoryStartIdMap.get( className );
    if( startId == null )
    {
      //create new entry for this category session ids
      startId = nextStartId;
      long numberOfKeys = sessionKeyEnum.getNumberOfKeys();
      nextStartId += numberOfKeys;
     
      categoryStartIdMap.put( className, startId );
    }
    return startId;
  }
}
