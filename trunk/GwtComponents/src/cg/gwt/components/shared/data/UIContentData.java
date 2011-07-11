package cg.gwt.components.shared.data;

import java.io.Serializable;

/*
 * the data used to build UI
 * As the Object class can't be used on the web client side. use this class as the top super class for all ui data
 */
public class UIContentData< D extends UIResourceData > implements Serializable
{
  private static final long serialVersionUID = -2859410726704766317L;

  private D resourceData;

  public D getResourceData()
  {
    return resourceData;
  }

  public void setResourceData( D resourceData )
  {
    this.resourceData = resourceData;
  }
}
