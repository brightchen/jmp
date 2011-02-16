package cg.gwt.services.client;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ISessionManagementServiceAsync
{
  public void getStringValue( String key, AsyncCallback< String > callback );
  public void getStringValues( List< String > keys, AsyncCallback< Map< String, String > > callback );

  public void setStringValue( String key, String value, AsyncCallback< Void > callback );
  public void setStringValues( Map< String, String > keyValues, AsyncCallback< Void > callback );

}
