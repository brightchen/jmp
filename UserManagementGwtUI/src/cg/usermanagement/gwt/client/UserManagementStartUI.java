package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.CompositeUI;
import cg.usermanagement.gwt.client.login.LoginStartUI;
import cg.usermanagement.gwt.client.register.UserRegisterUI;
import cg.usermanagement.gwt.shared.data.UserManagementStartData;

import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserManagementStartUI extends CompositeUI< UserManagementStartData, TabPanel >
{
  private String[] tabTitles;
  
  public UserManagementStartUI( UserManagementStartData data )
  {
    setData( data );
    LoginStartUI loginStartUI = new LoginStartUI( data.getUserLoginData(), data.getAccountLoginData() );
    UserRegisterUI registerUI = new UserRegisterUI( data.getUserRegisterData() );
    addChild( loginStartUI );
    addChild( registerUI );
    
    tabTitles = new String[2];
    tabTitles[0] = data.getResourceData().getLoginTitle();
    tabTitles[1] = data.getResourceData().getRegisterTitle();

    setContainer( new TabPanel() );
  }

  @Override
  protected void addChildToContainer( TabPanel theContainer, Widget child, int index )
  {
    theContainer.add( child, tabTitles[index] );
  }
  
  @Override
  protected void afterAddingChildren()
  {
    getContainer().selectTab( 0 );
  }

}
