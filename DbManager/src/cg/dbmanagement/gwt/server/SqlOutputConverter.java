package cg.dbmanagement.gwt.server;

import cg.common.converter.ConvertNotSupportException;
import cg.common.converter.Converter;
import cg.dbmanagement.gwt.shared.data.ColumnData;
import cg.dbmanagement.gwt.shared.data.QueryOutputData;
import cg.dbmanagement.gwt.shared.data.SqlOutputData;
import cg.dbmanagement.gwt.shared.data.UpdateOutputData;
import cg.persistence.model.ColumnInfo;
import cg.persistence.model.QueryOutput;
import cg.persistence.model.SqlOutput;
import cg.persistence.model.UpdateOutput;

public class SqlOutputConverter
{
  public static SqlOutputData convertSqlOutput( SqlOutput output )
  {
    
  }
  
  public static QueryOutputData convertQueryOutput( QueryOutput output )
  {
    QueryOutputData data = new QueryOutputData();
  }
  
  public static UpdateOutputData convertUpdateOutput( UpdateOutput output )
  {
    return new UpdateOutputData( output.getOutput() );
  }
  
  public static ColumnData convertColumnInfo( ColumnInfo info )
  {
    ColumnData data = new ColumnData();
    data.setName( info.getMetaData().getName() );
    data.setLabel( info.getMetaData().getLabel() );
    String className = info.getMetaData().getClassName();
    for( Object rowData : info.getData() )
    {
      data.addRow( convertObjectToString( rowData, className ) );
    }
    return data;
  }
  
  public static String convertObjectToString( Object obj, String className )
  {
    try
    {
      return Converter.getConverter( Converter.StrategyEnum.toStringStrategy ).convert( obj, String.class );
    }
    catch( ConvertNotSupportException e )
    {
      return "";
    }
  }
}
