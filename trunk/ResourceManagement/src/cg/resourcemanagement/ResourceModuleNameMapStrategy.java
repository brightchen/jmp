package cg.resourcemanagement;

import java.util.HashMap;
import java.util.Map;

import cg.common.property.ClassProperty;

/*
 * get the resource module name from the configure map
 */
public class ResourceModuleNameMapStrategy implements IResourceModuleNameStrategy
{
  public static ResourceModuleNameMapStrategy defaultInstance = new ResourceModuleNameMapStrategy();
  
  //keep the map packageName ==> module name
  private Map< String, String > packageModuleMap = new HashMap< String, String >();

  public ResourceModuleNameMapStrategy()
  {
    putBuildinPackageModule();
  }
  
  @Override
  public String getResourceModuleName( ClassProperty resourceDataProperty, Class<?> ownerContentDataClass, Class<?> ownerResourceDataClass )
  {
    if( ownerContentDataClass != null )
    {
      String moduleName = packageModuleMap.get( ownerContentDataClass.getPackage().getName() );
      if( moduleName != null )
        return moduleName;
    }

    if( ownerResourceDataClass != null )
    {
      String moduleName = packageModuleMap.get( ownerResourceDataClass.getPackage().getName() );
      if( moduleName != null )
        return moduleName;
    }

    return null;
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
