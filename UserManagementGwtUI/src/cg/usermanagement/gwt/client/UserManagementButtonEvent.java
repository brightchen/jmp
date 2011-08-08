package cg.usermanagement.gwt.client;

import java.util.List;

import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.gwt.components.shared.data.ResponseData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelOperation;

public class UserManagementButtonEvent extends UserManagementEvent< UserManagementPanelOperation >
{
  private UserManagementPanelOperation data;
  
  @Override
  public void fire()
  {
    getUserManagement().onUserManagementPanelOperation( data,
                                                        new PopupFailureReasonCallback< List< ResponseData<?> > >()
                                                        {
                                                          @Override
                                                          public void onSuccess( List< ResponseData<?> > responseDatas )
                                                          {
                                                            onUserManagementPanelOperationSuccess( data, responseDatas );
                                                          }
                                                        } );
  }
  
  @Override
  public UserManagementPanelOperation getData()
  {
    return data;
  }

  /*
   * this event don't need dynamic data from ui. So, we can use setData
   * the data here is just for distinguishing which UserManagement button triggered this event. 
   */
  public void setData( UserManagementPanelOperation data )
  {
    this.data = data;
  }
  
  protected void onUserManagementPanelOperationSuccess( UserManagementPanelOperation operation, List< ResponseData<?> > responseDatas )
  {
    UserManagementUIFlow.onUserManagementPanelOperationSuccess( operation, responseDatas );
  }
}
