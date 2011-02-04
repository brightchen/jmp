package cg.dbmanagement.gwt.client;

import cg.dbmanagement.gwt.client.ui.DbUserLoginPart;
import cg.usermanagement.gwt.client.ui.SystemUserLoginPart;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DbManager implements EntryPoint
{
  private DbUserLoginPart dbLoginPart;
  
  public void onModuleLoad()
  {
    //TabLayoutPanel seems don't work and TabPanel doesn't deprecated in gwt2.1.1
    // TabLayoutPanel tp = new TabLayoutPanel( 1.5, Unit.EM );
    //use the lazy initialize
    TabPanel tp = new TabPanel();
    tp.add( buildSystemLogin(), "System" );
    tp.add( buildDatabaseLogin(), "Database" );
    tp.addSelectionHandler( new SelectionHandler< Integer >()
                            {
                              @Override
                              public void onSelection( SelectionEvent<Integer> event )
                              {
                                Integer selectItemIndex = event.getSelectedItem();
                                if( selectItemIndex == 1 )
                                {
                                  //the database login tab is selected
                                  dbLoginPart.load();
                                }
                              }
                            } );

    tp.selectTab( 0 );

    //this module using UserManagementGwtUI, remove the widget added by UserManagementGwtUI
    RootPanel.get().remove( 0 );

    // Add it to the root panel.
    RootPanel.get().add( tp );

  }

  protected Widget buildSystemLogin()
  {
    SystemUserLoginPart systemLoginPart = new SystemUserLoginPart();
    
    return systemLoginPart.build();
  }

  protected Widget buildDatabaseLogin()
  {
    dbLoginPart = new DbUserLoginPart();
    
    return dbLoginPart.build();

  }
  

}
