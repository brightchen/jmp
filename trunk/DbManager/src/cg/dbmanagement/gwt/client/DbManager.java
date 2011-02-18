package cg.dbmanagement.gwt.client;

import cg.dbmanagement.gwt.client.ui.DbUserLoginBuilder;
import cg.dbmanagement.gwt.client.ui.QueryInputWidgetBuilder;
import cg.dbmanagement.gwt.client.ui.QueryPart;
import cg.dbmanagement.gwt.client.ui.QueryResultWidgetBuilder;
import cg.dbmanagement.gwt.shared.data.DbUserLoginData;
import cg.dbmanagement.gwt.shared.data.SessionAttribute;
import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.gwt.services.client.ISessionManagementService;
import cg.gwt.services.client.ISessionManagementServiceAsync;
import cg.usermanagement.gwt.client.ui.SystemUserLoginPart;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DbManager implements EntryPoint
{
  private DbUserLoginBuilder dbLoginBuilder;
  private TabPanel tabPanel;
  private SplitLayoutPanel queryPanel;

  //services
  private ISessionManagementServiceAsync sessionService = GWT.create( ISessionManagementService.class );


  public void onModuleLoad()
  {
    //TabLayoutPanel seems don't work and TabPanel doesn't deprecated in gwt2.1.1
//     TabLayoutPanel tp = new TabLayoutPanel( 1.5, Unit.EM );
    //use the lazy initialize
    tabPanel = new TabPanel();
    tabPanel.add( buildSystemLogin(), "System" );
    tabPanel.add( buildDatabaseLogin(), "Database" );
    tabPanel.addSelectionHandler( new SelectionHandler< Integer >()
                            {
                              @Override
                              public void onSelection( SelectionEvent<Integer> event )
                              {
                                Integer selectItemIndex = event.getSelectedItem();
                                if( selectItemIndex == 1 )
                                {
                                  //the database login tab is selected
                                  dbLoginBuilder.refreshWidget();
                                }
                              }
                            } );

    tabPanel.selectTab( 0 );

    //this module using UserManagementGwtUI, remove the widget added by UserManagementGwtUI
    RootPanel.get().remove( 0 );

    // Add it to the root panel.
    RootLayoutPanel.get().add( tabPanel );
//    RootPanel.get().add( tabPanel );
//    addSplitLayoutPanel();
  }

  protected Widget buildSystemLogin()
  {
    SystemUserLoginPart systemLoginPart = new SystemUserLoginPart();
    
    return systemLoginPart.build();
  }

  protected Widget buildDatabaseLogin()
  {
    dbLoginBuilder = new DbUserLoginBuilder()
                  {
                    @Override
                    protected void onconnectToDBSuccess( String sessionIdentity )
                    {
                      //
                      sessionService.setStringValue( SessionAttribute.KEY.PERSISTENCE_SESSION_ID.name(),
                                                     sessionIdentity,
                                                     new PopupFailureReasonCallback< Void >() );

                      QueryInputWidgetBuilder input = new QueryInputWidgetBuilder();
                      QueryResultWidgetBuilder result = new QueryResultWidgetBuilder();
// the SplitLayoutPanel doesn't work properly     
//                      SplitLayoutPanel queryPanel = new SplitLayoutPanel();
//                      input.build();
//                      result.build();
//                      queryPanel.addWest(new HTML("navigation"), 128);
//                      queryPanel.addNorth(new HTML("list"), 384);
//                      queryPanel.add(new HTML("details"));
                      QueryPart queryPart = new QueryPart( input, result )
                      {
                        @Override
                        protected void doClose()
                        {
                          tabPanel.remove( 2 );
                        }
                      };
                      
                      VerticalPanel queryPanel = new VerticalPanel();
                      queryPart.setContainer( queryPanel );
//                      queryPanel.add( input.build() );
//                      queryPanel.add( result.build() );
                      tabPanel.add( queryPart.build(), "Query" );
                      tabPanel.selectTab( 2 );
                    }
                  };
    
    //set default information
    DbUserLoginData data = new DbUserLoginData();
    data.setJdbcUrl( "jdbc:derby:userdb;create=true" );
    data.setUserName( "user1" );
    data.setPassword( "user1" );
    dbLoginBuilder.setData( data );
    
    return dbLoginBuilder.build();

  }
  
  protected void addSplitLayoutPanel()
  {
    // Create a three-pane layout with splitters. 
    SplitLayoutPanel p = new SplitLayoutPanel();
    p.addWest(new HTML("navigation"), 128);
    p.addNorth(new HTML("list"), 384);
    p.add(new HTML("details"));
    RootLayoutPanel rp = RootLayoutPanel.get();
    rp.add(p);
  }

}
