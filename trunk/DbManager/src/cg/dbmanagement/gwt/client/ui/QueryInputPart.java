package cg.dbmanagement.gwt.client.ui;

import cg.dbmanagement.gwt.shared.data.QueryInputData;
import cg.gwt.components.client.ui.Part;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextArea;

public class QueryInputPart extends Part< QueryInputData, FlexTable >
{
  private TextArea sqlField;
  
  @Override
  public FlexTable build()
  {
    FlexTable table = new FlexTable();
    QueryInputData data = getData();
    
    table.setText( 0, 0, "sql: " );

    sqlField = new TextArea();
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
    String sql = getData().getSql();
    
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
