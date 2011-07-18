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
  
  //resource key format: <module short name> + <class short name> + <property name>
  @Override
  public String getResourceKey( ClassProperty resourceDataProperty )
  {
    Class<?> ownerClass = resourceDataProperty.getClass().getDeclaringClass();
    String ownerClassShortName = ownerClass.getSimpleName();
    ownerClassShortName = ownerClassShortName.toLowerCase();
    ownerClassShortName = ownerClassShortName.endsWith( RESOURCE_CALSS_POSTFIX ) 
                        ? ownerClassShortName.substring( 0, ownerClassShortName.length() - RESOURCE_CALSS_POSTFIX.length() )
                        : ownerClassShortName;
    return getModuleShortName( resourceDataProperty ) + SEPERATOR + ownerClassShortName + SEPERATOR 
         + resourceDataProperty.getName().toLowerCase();
  }

  protected String getModuleShortName( ClassProperty resourceDataProperty )
  {
    return packageModuleMap.get( resourceDataProperty.getClass().getDeclaringClass().getPackage().getName() );
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
