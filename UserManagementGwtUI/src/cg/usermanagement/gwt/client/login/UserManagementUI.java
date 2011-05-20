package cg.usermanagement.gwt.client.login;

import cg.gwt.components.client.ui.UIComposite;
import cg.gwt.components.shared.data.UIPairData;

import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserManagementUI< UserLoginData, UserRegisterData > extends UIComposite< UIPairData< UserLoginData, UserRegisterData >, TabPanel >
{
  private static final String[] TAB_TITLE = { "User Login", "User Register" }; 
  public UserManagementUI( UserLoginData loginData, UserRegisterData registerData )
  {
    setData( new UIPairData< UserLoginData, UserRegisterData >( loginData, registerData ) );
    UserLoginUI loginUI = new UserLoginUI( (cg.usermanagement.gwt.shared.data.UserLoginData) loginData );
    UserRegisterUI registerUI = new UserRegisterUI( (cg.usermanagement.gwt.shared.data.UserRegisterData) registerData );
    addChild( loginUI );
    addChild( registerUI );
    setContainer( new TabPanel() );
  }

  @Override
  protected void addChildToContainer( TabPanel theContainer, Widget child, int index )
  {
    theContainer.add( child, TAB_TITLE[index] );
  }
  
  @Override
  protected void afterAddingChildren()
  {
    getContainer().selectTab( 0 );
  }

}
