package cg.resourcemanagement;



/*
 * this class keep the context of resource data property
 * it is useful when inject the resource
 *
 * superResourceKey: the resource key of super content data class, this parameter is used 
 *   if part of all the resource key is inherited from super
 */
public class ResourcePropertyContext
{
  private Class<?> ownerContentDataClass;
  private Class<?> resourceDataClass;
  /*
   * the resource key which inherited from ResourceData/ResourceContent
   */
  private ResourceKey superResourceKey;
  
  public ResourcePropertyContext(){}
  
  public ResourcePropertyContext( Class<?> ownerContentDataClass, ResourceKey superResourceKey, Class<?> resourceDataClass )
  {
    setOwnerContentDataClass( ownerContentDataClass );
    setResourceDataClass( resourceDataClass );
    setSuperResourceKey( superResourceKey );
  }
  
  public Class< ? > getResourceDataClass()
  {
    return resourceDataClass;
  }
  public void setResourceDataClass( Class< ? > resourceDataClass )
  {
    this.resourceDataClass = resourceDataClass;
  }

  public Class< ? > getOwnerContentDataClass()
  {
    return ownerContentDataClass;
  }

  public void setOwnerContentDataClass( Class< ? > ownerContentDataClass )
  {
    this.ownerContentDataClass = ownerContentDataClass;
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
