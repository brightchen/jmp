package cg.gwt.components.client.ui;

import cg.gwt.components.shared.data.UIObjectData;

import com.google.gwt.user.client.ui.UIObject;

public abstract class UIObjectBuilder< T extends UIObjectData, W extends UIObject > implements IUIObjectBuilder< W >
{
  private T data;
  
  public UIObjectBuilder(){}
  
  public UIObjectBuilder( T data )
  {
    setData( data );
  }

  public W build( T data )
  {
    setData( data );
    return build();
  }
  
  //empty implement
  @Override
  public void refreshWidget(){}
  
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