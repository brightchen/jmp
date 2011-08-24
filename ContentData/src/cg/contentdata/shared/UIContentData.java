package cg.contentdata.shared;

import java.io.Serializable;

/*
 * the data used to build UI
 * As the Object class can't be used on the web client side. use this class as the top super class for all ui data
 */
public class UIContentData< D extends ResourceData > implements Serializable
{
  private static final long serialVersionUID = -2859410726704766317L;

  // all the content data may have resource data, even if the Composite content data
  private D resourceData;
  
  public UIContentData()
  {
  }
  
  public D getResourceData()
  {
    return resourceData;
  }

  public void setResourceData( D resourceData )
  {
    this.resourceData = resourceData;
  }

}
