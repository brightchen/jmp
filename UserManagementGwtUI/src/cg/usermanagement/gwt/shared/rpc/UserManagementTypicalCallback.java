package cg.usermanagement.gwt.shared.rpc;

import cg.gwt.components.shared.data.FrameData;
import cg.gwt.components.shared.rpc.PopupFailureReasonCallback;
import cg.usermanagement.gwt.client.UserManagementUIFlow;

public class UserManagementTypicalCallback extends PopupFailureReasonCallback< FrameData >
{
  //seems one instance is enough
  public static UserManagementTypicalCallback instance = new UserManagementTypicalCallback();
  
  private UserManagementTypicalCallback(){}
  
  //just fresh page by default
  @Override
  public void onSuccess( FrameData frameData )
  {
    UserManagementUIFlow.refreshPage( frameData );
  }

}
