package cg.gwt.services.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cg.gwt.services.client.ISessionManagementService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SessionManagementServiceServlet extends RemoteServiceServlet implements ISessionManagementService
{
  private static final long serialVersionUID = -6250448657072026615L;

  @Override
  public String getStringValue( String key )
  {
    return (String)getSession().getAttribute( key );
  }
  
  public Map< String, String > getStringValues( List< String > keys )
  {
    if( keys == null )
      return null;
    
    Map< String, String > values = new HashMap< String, String >();
    for( String key : keys )
    {
      values.put( key, getStringValue( key ) );
    }
    return values;
  }

  public void setStringValue( String key, String value )
  {
    getSession().setAttribute( key, value );
  }
  
  public void setStringValues( Map< String, String > keyValues )
  {
    HttpSession session = getSession();
    for( Map.Entry< String, String > entry : keyValues.entrySet() )
    {
      session.setAttribute( entry.getKey(), entry.getValue() );
    }
  }
  
  protected HttpSession getSession()
  {
    return getThreadLocalRequest().getSession(true);
  }

}
