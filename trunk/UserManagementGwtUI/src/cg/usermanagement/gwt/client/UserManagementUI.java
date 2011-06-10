package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.UIComposite;
import cg.gwt.components.shared.data.UIPairData;
import cg.usermanagement.gwt.client.login.LoginStartUI;
import cg.usermanagement.gwt.client.register.UserRegisterUI;
import cg.usermanagement.gwt.shared.data.AccountLoginData;
import cg.usermanagement.gwt.shared.data.UserLoginData;
import cg.usermanagement.gwt.shared.data.UserRegisterData;

import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserManagementUI extends UIComposite< UIPairData< UIPairData< UserLoginData, AccountLoginData >, UserRegisterData >, TabPanel >
{
  private static final String[] TAB_TITLE = { "Login", "User Register" }; 
  public UserManagementUI( UserLoginData userLoginData, AccountLoginData accountLoginData, UserRegisterData registerData )
  {
    UIPairData< UserLoginData, AccountLoginData > loginPairData = new UIPairData< UserLoginData, AccountLoginData >( userLoginData, accountLoginData );
    setData( new UIPairData< UIPairData< UserLoginData, AccountLoginData >, UserRegisterData >( loginPairData, registerData ) );
    
    LoginStartUI loginStartUI = new LoginStartUI( userLoginData, accountLoginData );
    UserRegisterUI registerUI = new UserRegisterUI( registerData );
    addChild( loginStartUI );
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
