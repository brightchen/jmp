package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.event.UIEvent;
import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.gwt.components.shared.data.UIFlowData;

import com.google.gwt.core.client.GWT;

public class UserManagementStartEvent extends UIEvent< Void >
{
  private IUserManagementAsync userManagement = GWT.create( IUserManagement.class );
  
  @Override
  public void fire()
  {
    userManagement.getStartUI( new PopupFailureReasonCallback< UIFlowData >()
                                {
                                  @Override
                                  public void onSuccess( UIFlowData uiFlowData )
                                  {
                                    onGetStartUISuccess( uiFlowData );
                                  }
                                } );
  }
  
  @Override
  public Void getData()
  {
    return null;
  }

  protected void onGetStartUISuccess( UIFlowData uiFlowData )
  {
    UserManagementUIFlow.doGetStartUISuccess( uiFlowData );
  }
}
