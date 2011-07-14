package cg.usermanagement.gwt.server.resource;

import java.lang.reflect.Method;

import cg.common.property.ClassProperty;
import cg.common.util.ReflectionUtil;
import cg.usermanagement.gwt.shared.data.UserManagementResourceData;


/*
 * this manager manages the properties of resource data and resource key
 * the resource data class maybe contain other resource data classes
 */
public class UserManagementResourceDataManager
{
  // ResourceData class ==> ( ResourceDataProperty ==> Resource Key )
  private IResourcePropertyKeyLookupStrategy lookupStrategy = new ResourcePropertyKeyChainLookupStrategy();
  
  public static final UserManagementResourceDataManager defaultInstance = new UserManagementResourceDataManager();

  public UserManagementResourceDataManager(){ }
  
  public UserManagementResourceDataManager( IResourcePropertyKeyLookupStrategy lookupStrategy )
  {
    setLookupStrategy( lookupStrategy );
  }
  
  public String getResourceKey( ClassProperty resourceDataProperty )
  {
    return lookupStrategy.getResourceKey( resourceDataProperty );
  }

  public IResourcePropertyKeyLookupStrategy getLookupStrategy()
  {
    return lookupStrategy;
  }

  public void setLookupStrategy( IResourcePropertyKeyLookupStrategy lookupStrategy )
  {
    this.lookupStrategy = lookupStrategy;
  }
  
  /*
   * set the resource value to the ResourceData according to the class property
   */
  public static < RD extends UserManagementResourceData > void setResourceValue( RD resourceData, ClassProperty classProperty, String resourceValue )
  {
    Method setterMethod = ReflectionUtil.getMethod( resourceData.getClass(), classProperty.getSetterMethodName(), new Object[]{ String.class } );
    try
    {
      setterMethod.invoke( resourceData, resourceValue );
    }
    catch( Exception e )
    {
      //it should treat as RuntimeException
    }
  }
  
}
