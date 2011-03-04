package cg.persistence.model;

import java.util.List;

public class QueryOutput extends SqlOutput
{
  private static final long serialVersionUID = 520007991074799587L;
  
  private List< ColumnInfo > columnInfos;
  public QueryOutput( List< ColumnInfo > columnInfos )
  {
    setOutputType( SqlOutputType.QUERY );
    setColumnInfos( columnInfos );
  }
  
  @SuppressWarnings( "unchecked" )
  @Override
  public List< ColumnInfo > getOutput()
  {
    return columnInfos;
  }

  public List< ColumnInfo > getColumnInfos()
  {
    return columnInfos;
  }

  public void setColumnInfos( List< ColumnInfo > columnInfos )
  {
    this.columnInfos = columnInfos;
  }


  

}
