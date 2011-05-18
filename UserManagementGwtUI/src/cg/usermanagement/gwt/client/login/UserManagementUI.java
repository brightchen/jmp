package cg.usermanagement.gwt.client.login;

import cg.gwt.components.client.ui.UIComposite;
import cg.gwt.components.shared.data.UIPairData;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class UserManagementUI< UserLoginData, UserRegisterData > extends UIComposite< UIPairData< UserLoginData, UserRegisterData >, FlexTable >
{
  private UserLoginUI loginUI;
  private UserRegisterUI registerUI;
  
  public UserManagementUI( UserLoginData loginData, UserRegisterData registerData )
  {
    setData( new UIPairData< UserLoginData, UserRegisterData >( loginData, registerData ) );
    loginUI = new UserLoginUI( (cg.usermanagement.gwt.shared.data.UserLoginData) loginData );
    registerUI = new UserRegisterUI( (cg.usermanagement.gwt.shared.data.UserRegisterData) registerData );
    addChild( loginUI );
    addChild( registerUI );
    setContainer( new FlexTable() );
  }

  @Override
  protected void addChildComponent( Widget child, int index )
  {
    if( index == 0 )
      getContainer().setWidget( 0, 0, child );
    else if( index == 1 )
      getContainer().setWidget( 0, 1, child );
  }

}
