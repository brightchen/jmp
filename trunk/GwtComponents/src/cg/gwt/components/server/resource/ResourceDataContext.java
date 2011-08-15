package cg.gwt.components.server.resource;

import cg.gwt.components.shared.data.UIContentData;
import cg.resourcemanagement.ResourceKey;

/*
 * this class keep the context of resource data
 * it is useful when inject the resource
 *
 * ownerContentDataClass: the content data class which owns the resource data class of this property
 * resourceDataClass: the class of resource data which is going to inject value
 */
public class ResourceDataContext
{
  private UIContentData ownerContentData; 
  private ResourceKey superResourceKey;

  public ResourceDataContext(){}
  public ResourceDataContext( UIContentData ownerContentData, ResourceKey superResourceKey )
  {
    setOwnerContentData( ownerContentData );
    setSuperResourceKey( superResourceKey );
  }
  

  public UIContentData getOwnerContentData()
  {
    return ownerContentData;
  }
  public void setOwnerContentData( UIContentData ownerContentData )
  {
    this.ownerContentData = ownerContentData;
  }
  public ResourceKey getSuperResourceKey()
  {
    return superResourceKey;
  }
  public void setSuperResourceKey( ResourceKey superResourceKey )
  {
    this.superResourceKey = superResourceKey;
  }

}
