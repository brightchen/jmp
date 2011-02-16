package cg.dbmanagement.gwt.shared.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ColumnData implements Serializable
{
  private static final long serialVersionUID = 92575249880428742L;

  private String name;
  private String label;   //the title for display

  private List< String > data;
  
  public ColumnData()
  {
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getLabel()
  {
    return label;
  }

  public void setLabel( String label )
  {
    this.label = label;
  }

  public List< String > getData()
  {
    return data;
  }

  public void setData( List< String > data )
  {
    this.data = data;
  }

  public void addRow( String rowData )
  {
    if( data == null )
      data = new ArrayList< String >();
    data.add( rowData );
  }
}
