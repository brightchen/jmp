package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.UIComposite;
import cg.usermanagement.gwt.client.login.LoginStartUI;
import cg.usermanagement.gwt.client.register.UserRegisterUI;
import cg.usermanagement.gwt.shared.data.UserManagementData;

import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserManagementUI extends UIComposite< UserManagementData, TabPanel >
{
  private static final String[] TAB_TITLE = { "Login", "User Register" }; 
  
  public UserManagementUI( UserManagementData data )
  {
    setData( data );
    LoginStartUI loginStartUI = new LoginStartUI( data.getUserLoginData(), data.getAccountLoginData() );
    UserRegisterUI registerUI = new UserRegisterUI( data.getUserRegisterData() );
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
