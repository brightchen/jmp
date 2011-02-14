package cg.gwt.services.client;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath( "sessionManagementService" )
public interface ISessionManagementService extends RemoteService
{
  public Object getValue( String key );
  public String getStringValue( String key );
  public Map< String, Object > getValues( List< String > keys );
  public Map< String, String > getStringValues( List< String > keys );

  public void setValue( String key, Object value );
  public void setStringValue( String key, String value );
  public void setValues( Map< String, Object > keyValues );
  public void setStringValues( Map< String, String > keyValues );
}
