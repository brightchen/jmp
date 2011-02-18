package cg.dbmanagement.gwt.client.ui;

import cg.dbmanagement.gwt.client.IPersistenceService;
import cg.dbmanagement.gwt.client.IPersistenceServiceAsync;
import cg.dbmanagement.gwt.shared.data.QueryInputData;
import cg.dbmanagement.gwt.shared.data.SessionAttribute;
import cg.dbmanagement.gwt.shared.data.SqlOutputData;
import cg.gwt.components.client.ui.Builder;
import cg.gwt.components.client.ui.IWidgetDigester;
import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.gwt.services.client.ISessionManagementService;
import cg.gwt.services.client.ISessionManagementServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;

public class QueryInputWidgetBuilder extends Builder< QueryInputData, FlexTable > implements IWidgetDigester
{
  private ISessionManagementServiceAsync sessionManagementService = GWT.create( ISessionManagementService.class );
  private IPersistenceServiceAsync persistenceService = GWT.create( IPersistenceService.class );
  private ISqlOutputHandler sqlOutputHandler;
  
  private TextArea sqlField;
  
  @Override
  public FlexTable build()
  {
    FlexTable table = new FlexTable();
    
    {
      QueryInputData data = getData();
      sqlField = new TextArea();
      sqlField.setWidth( "800px" );
      sqlField.setHeight( "400px" );
      sqlField.setText( data == null ? "" : data.getSql() );
      table.setWidget( 0, 0, sqlField );
    }

    {
      Panel actionPanel = new SimplePanel();
      Button executeButton = new Button( "execute" );
      executeButton.addClickHandler( new ClickHandler()
                                    {
                                      @Override
                                      public void onClick( ClickEvent event )
                                      {
                                        doExecuteSql();
                                      }
                                
                                    } );
      actionPanel.add( executeButton );
      
      table.setWidget( 1, 0, actionPanel );
    }

    return table;
  }

  protected void doExecuteSql()
  {
    digest();
    final String sql = getData().getSql();

    sessionManagementService.getStringValue( SessionAttribute.KEY.PERSISTENCE_SESSION_ID.name(), 
      new PopupFailureReasonCallback< String >()
      {
        @Override
        public void onSuccess( String result )
        {
          String sessionId = result;
          persistenceService.executeNativeSql( sessionId, sql, 
                                               new PopupFailureReasonCallback< SqlOutputData >()
                                               {
                                                @Override
                                                public void onSuccess( SqlOutputData outputData )
                                                {
                                                  handleSqlOutputData( outputData );
                                                }
                                               } );
            
        }
      } );
  }
  
  @Override
  public void digest()
  {
    getData().setSql( sqlField.getText() );
  }

  @Override
  protected QueryInputData createData()
  {
    return new QueryInputData();
  }

  protected void handleSqlOutputData( SqlOutputData outputData )
  {
    if( sqlOutputHandler == null )
      return;
    sqlOutputHandler.handleSqlOutput( outputData );
  }

  public ISqlOutputHandler getSqlOutputHandler()
  {
    return sqlOutputHandler;
  }
  public void setSqlOutputHandler( ISqlOutputHandler sqlOutputHandler )
  {
    this.sqlOutputHandler = sqlOutputHandler;
  }
  
  
}
