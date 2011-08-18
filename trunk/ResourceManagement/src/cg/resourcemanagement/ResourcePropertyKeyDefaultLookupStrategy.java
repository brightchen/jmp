package cg.resourcemanagement;

import cg.common.property.ClassProperty;

/*
 * statically transform the resourceDataProperty to the resource key
 */
public class ResourcePropertyKeyDefaultLookupStrategy implements IResourcePropertyKeyLookupStrategy
{
  private static ResourcePropertyKeyDefaultLookupStrategy defaultInstance;
  private final String RESOURCE_CALSS_POSTFIX = "resourcedata";

  public static ResourcePropertyKeyDefaultLookupStrategy defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( ResourcePropertyKeyDefaultLookupStrategy.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new ResourcePropertyKeyDefaultLookupStrategy();
        }
      }
    }
    return defaultInstance;
  }

//  private IResourceClassNameStrategy resourceClassNameStrategy = ResourceClassNameChainStrategy.defaultInstance;
//  private IResourcePropertyNameStrategy resourcePropertyNameStrategy = ResourcePropertyNameChainStrategy.defaultInstance; 
  
  public ResourcePropertyKeyDefaultLookupStrategy()
  {
  }
  
  /*
   * resource key format: <module short name> + <class short name> + <property name>
   * the resource owner class maybe different from the property declaring class 
   */
  @Override
  public ResourceKey getResourceKey( ClassProperty resourceDataProperty, ResourcePropertyContext context )
  {
    String moduleName = ResourceModuleNameConfigurator.defaultInstance.getResourceModuleName( resourceDataProperty, context );
    String className = getResourceClassName( resourceDataProperty, context );
    ResourceKey resourceKey = context.getSuperResourceKey();
    return ResourceKeyUtil.mergeResourceKey( ( resourceKey == null ) ? null : resourceKey.clone(),
                                             new ResourceKey( moduleName, className, getResourcePropertyName( resourceDataProperty ) ) );
  }


  public String getResourceClassName( ClassProperty resourceDataProperty, ResourcePropertyContext context )
  {
    if( context == null || context.getResourceDataClass() == null )
      return null;
    Class<?> ownerResourceDataClass = context.getResourceDataClass();
    String ownerClassShortName = ownerResourceDataClass.getSimpleName();
    ownerClassShortName = ownerClassShortName.toLowerCase();
    ownerClassShortName = ownerClassShortName.endsWith( RESOURCE_CALSS_POSTFIX ) 
                        ? ownerClassShortName.substring( 0, ownerClassShortName.length() - RESOURCE_CALSS_POSTFIX.length() )
                        : ownerClassShortName;
    return ownerClassShortName;
  }

  public String getResourcePropertyName( ClassProperty resourceDataProperty )
  {
    return resourceDataProperty.getName().toLowerCase();
  }

}
