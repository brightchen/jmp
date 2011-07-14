package cg.usermanagement.gwt.server.resource;

import cg.common.property.ClassProperty;


/*
 * this manager manages the properties of resource data and resource key
 * the resource data class maybe contain other resource data classes
 */
public class UserManagementResourceDataManager
{
  // ResourceData class ==> ( ResourceDataProperty ==> Resource Key )
  private ResourcePropertyKeyChainLookupStrategy lookupStrategy = new ResourcePropertyKeyChainLookupStrategy();

  public String getResourceKey( ClassProperty resourceDataProperty )
  {
    return lookupStrategy.getResourceKey( resourceDataProperty );
  }
  
}
