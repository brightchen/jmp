package cg.dbmanagement.gwt.client.ui;

import java.util.List;

import cg.dbmanagement.gwt.shared.data.ColumnData;
import cg.dbmanagement.gwt.shared.data.QueryOutputData;
import cg.dbmanagement.gwt.shared.data.QueryResultData;
import cg.dbmanagement.gwt.shared.data.SqlOutputData;
import cg.dbmanagement.gwt.shared.data.UpdateOutputData;
import cg.gwt.components.client.ui.Part;

import com.google.gwt.user.client.ui.FlexTable;

public class QueryResultPart extends Part< QueryResultData, FlexTable > implements ISqlOutputHandler
{
  private FlexTable container;
  
  @Override
  public FlexTable build()
  {
    container = new FlexTable();
    return container;
  }

  @Override
  public void updateData()
  {
    // TODO Auto-generated method stub
    
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
    if( SqlOutputData.OutputType.UPDATE.equals( outputData.getOutputType() ) )
    {
      UpdateOutputData data = (UpdateOutputData)outputData;
      container.setText( 0, 0, String.valueOf( data.getOutput() ) );
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
        container.setText( 0, columnIndex, label );
        
        List< String > rows = column.getData();
        if( rows != null && rows.size() > 0 )
        {
          int rowIndex = 0;
          for( String row : rows )
          {
            container.setText( rowIndex+1, columnIndex, row );
          }
          ++rowIndex;
        }
        ++columnIndex;
      }
    }
  }

}
