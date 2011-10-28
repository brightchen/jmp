package cg.usermanagement.gwt.client.menu;

import java.io.Serializable;

import cg.gwt.components.client.ui.event.UIMenuEvent;
import cg.gwt.components.shared.data.FrameData;
import cg.gwt.components.shared.data.MenuEventData;
import cg.gwt.components.shared.rpc.PopupFailureReasonCallback;
import cg.usermanagement.gwt.client.IUserManagement;
import cg.usermanagement.gwt.client.IUserManagementAsync;
import cg.usermanagement.gwt.client.UserManagementUIFlow;
import cg.usermanagement.gwt.shared.data.UserManagementMenuKey;

import com.google.gwt.core.client.GWT;

public class UserManagementMenuEvent extends UIMenuEvent implements Serializable 
{
  private static final long serialVersionUID = -3903627606324119377L;

  private IUserManagementAsync userManagementWebService = null;
  
  public UserManagementMenuEvent(){}
  
  public UserManagementMenuEvent( MenuEventData data )
  {
    super( data );
  }
  
  //create web service when event triggered to make sure the service is created at web client side
  protected IUserManagementAsync getUserManagementWebService()
  {
    if( userManagementWebService == null )
      userManagementWebService = GWT.create( IUserManagement.class );
    return userManagementWebService;
  }
  
  @Override
  public void fire()
  {
    String key = getData().getKey();
    if( UserManagementMenuKey.control$locale.getStringKey().equals( key ) )
    {
      fireLocaleMenuEvent( getData().getParameters().get( 0 ) );
    }
  }
  
  /*
   * set locale and refresh the page
   */
  public void fireLocaleMenuEvent( final String localeKey )
  {
    getUserManagementWebService().changeLocale( localeKey,
                                 new PopupFailureReasonCallback< FrameData >()
                                 {
                                   @Override
                                   public void onSuccess( FrameData frameData )
                                   {
                                     onChangeLocaleSuccess( frameData );
                                   }
                                 } );
  }

  protected void onChangeLocaleSuccess( FrameData frameData )
  {
    UserManagementUIFlow.refreshPage( frameData );
  }
  
  public UserManagementMenuEvent clone()
  {
    UserManagementMenuEvent newMenuEvent = new UserManagementMenuEvent();
    cloneTo( newMenuEvent );
    return newMenuEvent;
  }
  
  public void cloneTo( UserManagementMenuEvent newMenuEvent )
  {
    super.cloneTo( newMenuEvent );
    newMenuEvent.setData( this.getData() );
  }

}
