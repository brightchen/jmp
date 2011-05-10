package cg.usermanagement.gwt.client.ui;

import cg.gwt.components.client.ui.old.TypicalCompositeBuilder;
import cg.usermanagement.gwt.shared.data.MenuBarData;
import cg.usermanagement.gwt.shared.data.UserManagementClientPanelData;

import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Panel;

// UserManagement Panel includes menu and client panel
public class UserManagementPanelBuilder extends TypicalCompositeBuilder< MenuBarData, MenuBar, UserManagementMenuBarBuilder,
                                                                         UserManagementClientPanelData, Panel, 
                                                                         UserManagementClientPanelBuilder, Panel > 
{
  public UserManagementPanelBuilder( UserManagementMenuBarBuilder builder1, UserManagementClientPanelBuilder builder2 )
  {
    super( builder1, builder2 );
    // TODO Auto-generated constructor stub
  }
}
