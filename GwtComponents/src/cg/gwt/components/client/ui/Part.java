package cg.gwt.components.client.ui;

import cg.gwt.components.shared.data.PartData;

import com.google.gwt.user.client.ui.Widget;


//Part is a kind of UI Widget/Composite which represent a part of UI. which should be larger component than Widget.
//It should be combined by Widgets and can build itself.
public abstract class Part< T extends PartData, W extends Widget >
{
  private T data;
  
  public Part(){}
  
  public Part( T data )
  {
    setData( data );
  }

  //build ui according to the data
  public abstract W build();
  
  public W build( T data )
  {
    setData( data );
    return build();
  }
  
  //get the data from UI and set to data
  public abstract void updateData();
  
  //return an empty data instance if data not set. this avoid return null;
  public T getData()
  {
    if( data == null )
      data = createData();
    return data;
  }
  public void setData( T data )
  {
    this.data = data;
  }
  
  //create an empty data instance
  protected abstract T createData();
}