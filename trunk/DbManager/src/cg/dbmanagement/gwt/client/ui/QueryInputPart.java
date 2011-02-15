package cg.dbmanagement.gwt.client.ui;

import java.util.List;
import java.util.Map;

import cg.dbmanagement.gwt.client.IPersistenceService;
import cg.dbmanagement.gwt.client.IPersistenceServiceAsync;
import cg.dbmanagement.gwt.shared.data.QueryInputData;
import cg.dbmanagement.gwt.shared.data.SessionAttribute;
import cg.gwt.components.client.ui.Part;
import cg.gwt.services.client.ISessionManagementService;
import cg.gwt.services.client.ISessionManagementServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextArea;

public class QueryInputPart extends Part< QueryInputData, FlexTable >
{
  private ISessionManagementServiceAsync sessionManagementService = GWT.create( ISessionManagementService.class );
  private IPersistenceServiceAsync persistenceService = GWT.create( IPersistenceService.class );

  private TextArea sqlField;
  
  @Override
  public FlexTable build()
  {
    FlexTable table = new FlexTable();
    QueryInputData data = getData();
    
    table.setText( 0, 0, "sql: " );

    sqlField = new TextArea();
    sqlField.setWidth( "800px" );
    sqlField.setHeight( "400px" );
    sqlField.setText( data == null ? "" : data.getSql() );
    table.setWidget( 0, 1, sqlField );

    Button executeButton = new Button( "execute" );
    executeButton.addClickHandler( new ClickHandler()
                                  {
                                    @Override
                                    public void onClick( ClickEvent event )
                                    {
                                      doExecuteSql();
                                    }
                              
                                  } );
    table.setWidget( 0, 2, executeButton );

    return table;
  }

  protected void doExecuteSql()
  {
    updateData();
    final String sql = getData().getSql();

    sessionManagementService.getStringValue( SessionAttribute.KEY.PERSISTENCE_SESSION_ID.name(), 
      new AsyncCallback< String >()
      {
        @Override
        public void onFailure( Throwable caught )
        {
          // TODO Auto-generated method stub
        
        }
      
        @Override
        public void onSuccess( String result )
        {
          String sessionId = result;
          persistenceService.executeNativeQuery( sessionId, sql, 
             new AsyncCallback< Map< String, List< Object > > >()
             {
              @Override
              public void onFailure( Throwable caught )
              {
                // TODO Auto-generated method stub
              }
            
              @Override
              public void onSuccess( Map< String, List< Object > > result )
              {
                
              }
            
             } );
            
          }
       } );
  }
  
  @Override
  public void updateData()
  {
    getData().setSql( sqlField.getText() );
  }

  @Override
  protected QueryInputData createData()
  {
    return new QueryInputData();
  }

}
