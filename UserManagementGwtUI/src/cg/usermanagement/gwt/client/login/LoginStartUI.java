package cg.usermanagement.gwt.client.login;

import cg.gwt.components.client.ui.CompositeUI;
import cg.gwt.components.shared.data.UIPairData;
import cg.usermanagement.gwt.shared.data.AccountLoginData;
import cg.usermanagement.gwt.shared.data.UserLoginData;

import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginStartUI extends CompositeUI< UIPairData< UserLoginData, AccountLoginData >, TabPanel >
{
  private String[] tabTitles;
  public LoginStartUI( UserLoginData userLoginData, AccountLoginData accountLoginData )
  {
    setData( new UIPairData< UserLoginData, AccountLoginData >( userLoginData, accountLoginData ) );
    LoginUI userLoginUI = new LoginUI( userLoginData );
    addChild( userLoginUI );
    if( accountLoginData != null )   //accountLoginData means don't need account login section
    {
      LoginUI accountLoginUI = new LoginUI( accountLoginData );
      addChild( accountLoginUI );
      
      tabTitles = new String[2];
      tabTitles[1] = accountLoginData.getResourceData().getTitle();
    }
    else
    {
      tabTitles = new String[1];
    }
    
    tabTitles[0] = userLoginData.getResourceData().getTitle();
    
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
