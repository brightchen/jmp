package cg.usermanagement.gwt.client.menu;

import java.util.List;

import com.google.gwt.core.client.GWT;

import cg.gwt.components.client.ui.event.UIMenuEvent;
import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.gwt.components.shared.data.MenuEventData;
import cg.gwt.components.shared.data.ResponseData;
import cg.usermanagement.gwt.client.IUserManagement;
import cg.usermanagement.gwt.client.IUserManagementAsync;
import cg.usermanagement.gwt.client.UserManagementUIFlow;
import cg.usermanagement.gwt.shared.data.UserManagementMenuKey;

public class UserManagementMenuEvent extends UIMenuEvent
{
  private IUserManagementAsync userManagement = GWT.create( IUserManagement.class );
  
  public UserManagementMenuEvent( MenuEventData data )
  {
    super( data );
  }
  
  @Override
  public void fire()
  {
    String key = getData().getKey();
    if( UserManagementMenuKey.control$locale.equals( key ) )
    {
      fireLocaleMenuEvent( getData().getParameters().get( 0 ) );
    }
  }
  
  /*
   * set locale and refresh the page
   */
  public void fireLocaleMenuEvent( final String localeKey )
  {
    userManagement.changeLocale( localeKey,
                                 new PopupFailureReasonCallback< List< ResponseData<?> > >()
                                 {
                                   @Override
                                   public void onSuccess( List< ResponseData<?> > responseDatas )
                                   {
                                     onChangeLocaleSuccess( responseDatas );
                                   }
                                 } );
  }

  protected void onChangeLocaleSuccess( List< ResponseData<?> > responseDatas )
  {
    UserManagementUIFlow.freshCurrentPage( responseDatas );
  }
}
