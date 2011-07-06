package cg.usermanagement.gwt.client;

import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.shared.data.MenuBarData;
import cg.usermanagement.gwt.client.login.SystemUserLoginTransformer;
import cg.usermanagement.gwt.client.ui.MenuPanelBuilder;
import cg.usermanagement.gwt.client.ui.UserManagementClientPanelBuilder;
import cg.usermanagement.gwt.client.ui.UserManagementMenuBarBuilder;
import cg.usermanagement.gwt.client.ui.UserManagementPanelBuilder;
import cg.usermanagement.gwt.shared.data.UserManagementMenuBarData;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TestPage implements EntryPoint
{
  public void onModuleLoad()
  {
//    addTestMenuBarBuilder();
    addTestMenuPanelBuilder();
  }    

  protected void normalFlow()
  {
    SystemUserLoginTransformer loginPart = new SystemUserLoginTransformer();
    
    FlexTable table = loginPart.build();
    
    RootPanel rp = RootPanel.get();
    rp.add( table );
  }

  protected void addTestMenuPanelBuilder()
  {
    MenuBarData menuBarData = UserManagementMenuBarData.getTypicalData();
    List< MenuBarData > menuPanelData = new ArrayList< MenuBarData >();
    for( int i = 0; i < 4; ++i )
    {
      menuPanelData.add( menuBarData );
    }
    
    MenuPanelBuilder menuPanelBuilder = new MenuPanelBuilder();
    RootPanel.get().add( menuPanelBuilder.build( menuPanelData ) );
  }
  
  protected void addTestMenuBarBuilder()
  {
    UserManagementMenuBarBuilder menuBuilder = new UserManagementMenuBarBuilder();
    UserManagementClientPanelBuilder clientBuilder = new UserManagementClientPanelBuilder();
    UserManagementPanelBuilder panelBuilder = new UserManagementPanelBuilder( menuBuilder, clientBuilder );
    panelBuilder.setContainer( new VerticalPanel() );
    RootPanel rp = RootPanel.get();
    rp.add( panelBuilder.build() );

  }
  protected void addTestMenuBar()
  {
    // Make a command that we will execute from all leaves.
    Command cmd = new Command() {
      public void execute() {
        Window.alert("You selected a menu item!");
      }
    };

    // Make some sub-menus that we will cascade from the top menu.
    MenuBar fooMenu = new MenuBar(true);
    fooMenu.addItem("the", cmd);
    fooMenu.addItem("foo", cmd);
    fooMenu.addItem("menu", cmd);

    MenuBar barMenu = new MenuBar(true);
    barMenu.addItem("the", cmd);
    barMenu.addItem("bar", cmd);
    barMenu.addItem("menu", cmd);

    MenuBar bazMenu = new MenuBar(true);
    bazMenu.addItem("the", cmd);
    bazMenu.addItem("baz", cmd);
    bazMenu.addItem("menu", cmd);

    // Make a new menu bar, adding a few cascading menus to it.
    MenuBar menu = new MenuBar();
    menu.addItem("foo", fooMenu);
    menu.addItem("bar", barMenu);
    menu.addItem("baz", bazMenu);

    // Add it to the root panel.
    RootPanel.get().add(menu);
  }
  
}
