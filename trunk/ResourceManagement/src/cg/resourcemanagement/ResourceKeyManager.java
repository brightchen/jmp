package cg.resourcemanagement;

import cg.common.property.ClassProperty;


/*
 * this manager manages the resource key
 * it provides methods to get the resource keys, for example, from the ClassProperty
 */
public class ResourceKeyManager
{
  // ResourceData class ==> ( ResourceDataProperty ==> Resource Key )
  private IResourcePropertyKeyLookupStrategy lookupStrategy = new ResourcePropertyKeyChainLookupStrategy();
  
  public static final ResourceKeyManager defaultInstance = new ResourceKeyManager();

  public ResourceKeyManager(){ }
  
  public ResourceKeyManager( IResourcePropertyKeyLookupStrategy lookupStrategy )
  {
    setLookupStrategy( lookupStrategy );
  }
  
  public ResourceKey getResourceKey( ClassProperty resourceDataProperty )
  {
    return getResourceKey( resourceDataProperty, null );
  }
  
  /*
   * parameters
   * resourceDataProperty - the property of the resource data, one property corresponding to one field/getter/setter
   * ownerContentDataClass: the content data class which owns the resource data class of this property
   * ownerResourceDataClass: the resource data class which owns this property
   * superResourceKey: the resource key of super content data class, this parameter is used 
   *   if part of all the resource key is inherited from super
   */
  public ResourceKey getResourceKey( ClassProperty resourceDataProperty, ResourcePropertyContext context )
  {
    return lookupStrategy.getResourceKey( resourceDataProperty, context );
  }

  public IResourcePropertyKeyLookupStrategy getLookupStrategy()
  {
    return lookupStrategy;
  }

  public void setLookupStrategy( IResourcePropertyKeyLookupStrategy lookupStrategy )
  {
    this.lookupStrategy = lookupStrategy;
  }
  
}
