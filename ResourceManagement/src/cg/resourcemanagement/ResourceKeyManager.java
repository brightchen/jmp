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
  
  public String getResourceKey( ClassProperty resourceDataProperty )
  {
    return getResourceKey( resourceDataProperty, resourceDataProperty.getDeclaringClass() );
  }
  
  public String getResourceKey( ClassProperty resourceDataProperty, Class<?> resourceOwnerClass )
  {
    return lookupStrategy.getResourceKey( resourceDataProperty, resourceOwnerClass );
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
