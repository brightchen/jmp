package cg.persistence.model;

import java.util.ArrayList;
import java.util.List;

public class Column
{
  private ColumnMetaData metaData;
  private List< Object > data;
  
  public ColumnMetaData getMetaData()
  {
    return metaData;
  }
  public void setMetaData( ColumnMetaData metaData )
  {
    this.metaData = metaData;
  }
  
  public <T> void addRow( T row )
  {
    if( data == null )
      data = new ArrayList<Object>();
    data.add( row );
  }
  
  //convert the object to type T and add to the data list
  public void addObject( Object obj )
  {
    
  }
  
  public List< Object > getData()
  {
    return data;
  }
  public void setData( List< Object > data )
  {
    this.data = data;
  }
  public List< String > getDataAsStringList()
  {
    return null;
  }
  
  //rowIndex begins with 0
  public String convertRowToString( int rowIndex )
  {
    return null;
  }
}
