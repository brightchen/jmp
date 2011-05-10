package cg.usermanagement.gwt.client.ui;

import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.client.ui.old.UIObjectBuilder;
import cg.usermanagement.gwt.shared.data.MenuBarData;

import com.google.gwt.user.client.ui.MenuBar;

//The MenuBarBuilder only build menu bar with one root
//this builder build menu bar with one or multiple roots
public class MenuPanelBuilder extends UIObjectBuilder< List< MenuBarData >, MenuBar >
{
  private MenuBar rootMenuBar;
  @Override
  public MenuBar build()
  {
    List< MenuBarData > menuPanelData = getData();
    if( menuPanelData == null || menuPanelData.isEmpty() )
      return null;
    
    for( MenuBarData menuBarData : menuPanelData )
    {
      MenuBarBuilder builder = new MenuBarBuilder()
                                {
                                  @Override
                                  protected MenuBar addToRootMenu( String title, MenuBar menuBar )
                                  {
                                    //all the vertical menu bar shared the same root menu bar;
                                    MenuBar rootMenuBar = MenuPanelBuilder.this.getRootMenuBar();
                                    rootMenuBar.addItem( title, menuBar );
                                    return rootMenuBar;
                                  }
                                };
      
      builder.build( menuBarData );
    }
    
    return getRootMenuBar();
  }

  protected MenuBar getRootMenuBar()
  {
    if( rootMenuBar == null )
      rootMenuBar = new MenuBar( false );
    return rootMenuBar;
  }
  
  @Override
  protected List< MenuBarData > createData()
  {
    return new ArrayList< MenuBarData >();
  }

}
