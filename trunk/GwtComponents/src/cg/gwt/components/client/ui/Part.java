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
  
  //get the data from UI
  public abstract void updateData();
  
  public T getData()
  {
    return data;
  }
  public void setData( T data )
  {
    this.data = data;
  }
}