package cg.usermanagement.gwt.client.ui;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;

import cg.gwt.components.client.ui.UIObjectBuilder;
import cg.usermanagement.gwt.shared.data.UserManagementClientPanelData;

public class UserManagementClientPanelBuilder extends UIObjectBuilder< UserManagementClientPanelData, Panel >
{

  @Override
  public Panel build()
  {
    return new SimplePanel();
  }

  @Override
  protected UserManagementClientPanelData createData()
  {
    // TODO Auto-generated method stub
    return null;
  }

}
