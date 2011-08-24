package cg.contentdata.management;

import cg.contentdata.shared.UIContentData;
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
  /*
   * the content data which owns this resource data
   */
  private UIContentData<?> ownerContentData;
  /*
   * the resource key of this resource data, it can be injected by IResourceKey( ContentData, ResourceData, getter/setter )
   * or get from the name of ownerContentData
   */
  private ResourceKey resourceKey;

  public ResourceDataContext(){}
  public ResourceDataContext( UIContentData<?> ownerContentData, ResourceKey resourceKey )
  {
    setOwnerContentData( ownerContentData );
    setResourceKey( resourceKey );
  }
  
  //shallow copy
  @Override
  public ResourceDataContext clone()
  {
    return new ResourceDataContext( ownerContentData, resourceKey );
  }
  
  public UIContentData<?> getOwnerContentData()
  {
    return ownerContentData;
  }
  public void setOwnerContentData( UIContentData<?> ownerContentData )
  {
    this.ownerContentData = ownerContentData;
  }
  
  public ResourceKey getResourceKey()
  {
    return resourceKey;
  }
  public ResourceKey getNonNullResourceKey()
  {
    if( resourceKey == null )
      resourceKey = new ResourceKey();
    return resourceKey;
  }
  public void setResourceKey( ResourceKey resourceKey )
  {
    this.resourceKey = resourceKey;
  }

}
