package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.event.UIEvent;
import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.gwt.components.shared.data.ResponseData;

import com.google.gwt.core.client.GWT;

public class UserManagementStartEvent extends UIEvent< Void >
{
  private IUserManagementAsync userManagement = GWT.create( IUserManagement.class );
  
  @Override
  public void fire()
  {
    userManagement.getStartUI( new PopupFailureReasonCallback< ResponseData<?> >()
                                {
                                  @Override
                                  public void onSuccess( ResponseData<?> responseData )
                                  {
                                    onGetStartUISuccess( responseData );
                                  }
                                } );
  }
  
  @Override
  public Void getData()
  {
    return null;
  }

  protected void onGetStartUISuccess( ResponseData<?> responseData )
  {
    UserManagementUIFlow.doGetStartUISuccess( responseData );
  }
}
