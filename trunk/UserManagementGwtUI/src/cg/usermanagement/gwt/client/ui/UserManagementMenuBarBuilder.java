package cg.usermanagement.gwt.client.ui;

import cg.usermanagement.gwt.shared.data.UserManagementMenuBarData;

import com.google.gwt.user.client.ui.MenuBar;

public class UserManagementMenuBarBuilder extends MenuBarBuilder
{
  public UserManagementMenuBarBuilder()
  {
    //set default data
    setData( UserManagementMenuBarData.getTypicalData() );
  }
  
  @Override
  public MenuBar build()
  {
    return super.build();
  }

  @Override
  protected UserManagementMenuBarData createData()
  {
    return new UserManagementMenuBarData();
  }
  
}
