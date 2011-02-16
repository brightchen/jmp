package cg.gwt.services.client;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath( "gwtservices/sessionManagementService" )
public interface ISessionManagementService extends RemoteService
{
  public String getStringValue( String key );
  public Map< String, String > getStringValues( List< String > keys );

  public void setStringValue( String key, String value );
  public void setStringValues( Map< String, String > keyValues );
}
