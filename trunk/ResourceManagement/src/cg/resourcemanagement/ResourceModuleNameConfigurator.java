package cg.resourcemanagement;

import java.util.HashMap;
import java.util.Map;

import cg.common.property.ClassProperty;

/*
 * get the resource module name from the configure map
 */
public class ResourceModuleNameConfigurator
{
  public static ResourceModuleNameConfigurator defaultInstance = new ResourceModuleNameConfigurator();
  
  //keep the map packageName ==> module name
  private Map< String, String > packageModuleMap = new HashMap< String, String >();

  public ResourceModuleNameConfigurator()
  {
    putBuildinPackageModule();
  }
  
  /**
   * get the module name by resource data property and context.
   *   - get module name by resource data context. if get it, return. 
   *     this is used in case the resource data is used in different modules
   *   - get module name by resource data property. if get it, return
   * @param resourceDataProperty
   * @param context
   * @return
   */
  public String getResourceModuleName( ClassProperty resourceDataProperty, ResourcePropertyContext context )
  {
    Class<?> ownerContentDataClass = context.getOwnerContentDataClass();
    if( ownerContentDataClass != null )
    {
      String moduleName = packageModuleMap.get( ownerContentDataClass.getPackage().getName() );
      if( moduleName != null )
        return moduleName;
    }

    Class<?> ownerResourceDataClass = context.getResourceDataClass();
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
    addPackageModule( "cg.gwtui.shared.data", "gwtui");
    addPackageModule( "cg.sp.gwt.shared.data", "sp");
  }
  
  public void addPackageModule( String packageName, String moduleShortName )
  {
    packageModuleMap.put( packageName, moduleShortName );
  }


}
