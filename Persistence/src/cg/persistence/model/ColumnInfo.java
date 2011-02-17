package cg.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cg.common.converter.ConvertNotSupportException;
import cg.common.converter.Converter;

public class ColumnInfo implements Serializable
{
  private static final long serialVersionUID = 6875108251261808429L;

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
    ensureData();
    data.add( row );
  }
  
  //convert the object to type T and add to the data list
  public void addObject( Object obj )
  {
    ensureData();
    data.add( obj );
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
    try
    {
      Converter.convertType( data.get( rowIndex ), String.class );
    }
    catch( ConvertNotSupportException e )
    {
      e.printStackTrace();
    }
    return null;
  }
  
  protected List< Object > ensureData()
  {
    if( data == null )
      data = new ArrayList<Object>();
    return data;
  }
}
