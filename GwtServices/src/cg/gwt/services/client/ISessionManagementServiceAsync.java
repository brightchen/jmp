package cg.gwt.services.client;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ISessionManagementServiceAsync
{
  public void getValue( String key, AsyncCallback< Object > callback );
  public void getStringValue( String key, AsyncCallback< String > callback );
  public void getValues( List< String > keys, AsyncCallback< Map< String, Object > > callback );
  public void getStringValues( List< String > keys, AsyncCallback< Map< String, String > > callback );

  public void setValue( String key, Object value, AsyncCallback< Void > callback );
  public void setStringValue( String key, String value, AsyncCallback< Void > callback );
  public void setValues( Map< String, Object > keyValues, AsyncCallback< Void > callback );
  public void setStringValues( Map< String, String > keyValues, AsyncCallback< Void > callback );

}
