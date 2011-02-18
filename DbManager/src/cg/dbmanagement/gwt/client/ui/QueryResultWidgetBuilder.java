package cg.dbmanagement.gwt.client.ui;

import java.util.List;

import cg.dbmanagement.gwt.shared.data.ColumnData;
import cg.dbmanagement.gwt.shared.data.QueryOutputData;
import cg.dbmanagement.gwt.shared.data.QueryResultData;
import cg.dbmanagement.gwt.shared.data.SqlOutputData;
import cg.dbmanagement.gwt.shared.data.UpdateOutputData;
import cg.gwt.components.client.ui.Builder;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;

public class QueryResultWidgetBuilder extends Builder< QueryResultData, ScrollPanel > implements ISqlOutputHandler
{
  private ScrollPanel container;
  
  @Override
  public ScrollPanel build()
  {
    container = new ScrollPanel();
    return container;
  }

  @Override
  protected QueryResultData createData()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void handleSqlOutput( SqlOutputData outputData )
  {
    container.clear();
    
    FlexTable table = new FlexTable();
    if( SqlOutputData.OutputType.UPDATE.equals( outputData.getOutputType() ) )
    {
      UpdateOutputData data = (UpdateOutputData)outputData;
      table.setText( 0, 0, String.valueOf( data.getOutput() ) );
    }
    else
    {
      QueryOutputData data = (QueryOutputData)outputData;
      List< ColumnData > columns = data.getOutput();
      if( columns == null || columns.size() == 0 )
        return;
      int columnIndex = 0;
      for( ColumnData column : columns )
      {
        String label = column.getLabel();
        table.setText( 0, columnIndex, label );
        
        List< String > rows = column.getData();
        if( rows != null && rows.size() > 0 )
        {
          int rowIndex = 0;
          for( String row : rows )
          {
            table.setText( rowIndex+1, columnIndex, row );
            ++rowIndex;
          }
        }
        ++columnIndex;
      }
    }
    container.add( table );
  }

}
