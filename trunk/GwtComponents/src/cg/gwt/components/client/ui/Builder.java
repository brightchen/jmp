package cg.gwt.components.client.ui;

import cg.gwt.components.shared.data.WidgetData;

import com.google.gwt.user.client.ui.Widget;


//In fact, the original concept Part is really a builder.
//it build the widget use the data.
//So, refactor the Part to Builder.
//another concept is digest the data from the widget, the class for this processing is digester
public abstract class Builder< T extends WidgetData, W extends Widget >
{
  private T data;
  
  public Builder(){}
  
  public Builder( T data )
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