package cg.resourcemanagement;

import java.util.HashMap;
import java.util.Map;

import cg.common.property.ClassProperty;

/*
 * statically transform the resourceDataProperty to the resource key
 */
public class ResourcePropertyKeyDefaultLookupStrategy implements IResourcePropertyKeyLookupStrategy
{
  private final String RESOURCE_CALSS_POSTFIX = "resourcedata";
  private final String SEPERATOR = ".";

  //keep the map packageName ==> module short name
  private Map< String, String > packageModuleMap = new HashMap< String, String >();

  public ResourcePropertyKeyDefaultLookupStrategy()
  {
    putBuildinPackageModule();
  }
  
  /*
   * resource key format: <module short name> + <class short name> + <property name>
   * the resource owner class maybe different from the property declaring class 
   */
  @Override
  public String getResourceKey( ClassProperty resourceDataProperty, Class<?> resourceOwnerClass )
  {
    String ownerClassShortName = resourceOwnerClass.getSimpleName();
    ownerClassShortName = ownerClassShortName.toLowerCase();
    ownerClassShortName = ownerClassShortName.endsWith( RESOURCE_CALSS_POSTFIX ) 
                        ? ownerClassShortName.substring( 0, ownerClassShortName.length() - RESOURCE_CALSS_POSTFIX.length() )
                        : ownerClassShortName;
    return getModuleShortName( resourceOwnerClass ) + SEPERATOR + ownerClassShortName + SEPERATOR 
         + resourceDataProperty.getName().toLowerCase();
  }

  protected String getModuleShortName( Class<?> resourceOwnerClass )
  {
    return packageModuleMap.get( resourceOwnerClass.getPackage().getName() );
  }
  
  protected void putBuildinPackageModule()
  {
    addPackageModule( "cg.usermanagement.gwt.shared.data", "um" );
  }
  
  public void addPackageModule( String packageName, String moduleShortName )
  {
    packageModuleMap.put( packageName, moduleShortName );
  }
  
}
