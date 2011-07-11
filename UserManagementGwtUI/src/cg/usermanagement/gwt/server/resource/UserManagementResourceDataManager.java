package cg.usermanagement.gwt.server.resource;

import java.util.HashMap;
import java.util.Map;

import cg.gwt.components.shared.data.UIResourceData;

/*
 * this manager manages the properties of resource data and resource key
 * the resource data class maybe contain other resource data classes
 */
public class UserManagementResourceDataManager
{
  // ResourceData class ==> ( ResourceDataProperty ==> Resource Key )
  private Map< String, Map< IResourceDataProperty, UserManagementResourceKey > > propertyKeyMap = new HashMap< String, Map< IResourceDataProperty, UserManagementResourceKey > >();
  
  private ResourcePropertyKeyChainLookupStrategy lookupStrategy = new ResourcePropertyKeyChainLookupStrategy();
  
  public Map< IResourceDataProperty, UserManagementResourceKey > getResourcePropertyKeyMap( Class< ? extends UIResourceData > resouceDataClass )
  {
    return getResourcePropertyKeyMap( resouceDataClass.getName() );
  }
  
  public Map< IResourceDataProperty, UserManagementResourceKey > getResourcePropertyKeyMap( String resourceDataClassName )
  {
    return propertyKeyMap.get( resourceDataClassName );
  }
  
  public String getResourceKey( IResourceDataProperty resourceDataProperty )
  {
    return lookupStrategy.getResourceKey( resourceDataProperty );
  }
  
}
