package cg.usermanagement.gwt.client.login;

import cg.gwt.components.client.ui.UIComposite;
import cg.gwt.components.shared.data.UIPairData;
import cg.usermanagement.gwt.shared.data.AccountLoginData;
import cg.usermanagement.gwt.shared.data.UserLoginData;

import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginStartUI extends UIComposite< UIPairData< UserLoginData, AccountLoginData >, TabPanel >
{
  private static final String[] TAB_TITLE = { "User Login", "Account Login" }; 
  public LoginStartUI( UserLoginData userLoginData, AccountLoginData accountLoginData )
  {
    setData( new UIPairData< UserLoginData, AccountLoginData >( userLoginData, accountLoginData ) );
    LoginUI userLoginUI = new LoginUI( userLoginData );
    LoginUI accountLoginUI = new LoginUI( accountLoginData );
    addChild( userLoginUI );
    addChild( accountLoginUI );
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
