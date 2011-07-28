package cg.resourcemanagement;

import cg.common.property.ClassProperty;

/*
 * statically transform the resourceDataProperty to the resource key
 */
public class ResourcePropertyKeyDefaultLookupStrategy implements IResourcePropertyKeyLookupStrategy
{
  private final String SEPERATOR = ".";

  private IResourceModuleNameStrategy resourceModuleNameStrategy = ResourceModuleNameChainStrategy.defaultInstance;
  private IResourceClassNameStrategy resourceClassNameStrategy = ResourceClassNameChainStrategy.defaultInstance;
  private IResourcePropertyNameStrategy resourcePropertyNameStrategy = ResourcePropertyNameChainStrategy.defaultInstance; 
  
  public ResourcePropertyKeyDefaultLookupStrategy()
  {
  }
  
  /*
   * resource key format: <module short name> + <class short name> + <property name>
   * the resource owner class maybe different from the property declaring class 
   */
  @Override
  public String getResourceKey( ClassProperty resourceDataProperty, Class<?> ownerContentDataClass, Class<?> ownerResourceDataClass )
  {
    return getResourceModuleName( ownerContentDataClass, ownerResourceDataClass ) + SEPERATOR 
          + getResourceClassName( resourceDataProperty, ownerContentDataClass, ownerResourceDataClass ) 
          + SEPERATOR + getResourcePropertyName( resourceDataProperty );
  }

  protected String getResourceModuleName( Class<?> ownerContentDataClass, Class<?> ownerResourceDataClass )
  {
    return resourceModuleNameStrategy.getResourceModuleName( null, ownerContentDataClass, ownerResourceDataClass );
  }
  
  protected String getResourceClassName( ClassProperty resourceDataProperty, Class<?> ownerContentDataClass, Class<?> ownerResourceClass )
  {
    return resourceClassNameStrategy.getResourceClassName( resourceDataProperty, ownerContentDataClass, ownerResourceClass );
  }
  
  protected String getResourcePropertyName( ClassProperty resourceDataProperty )
  {
    return resourcePropertyNameStrategy.getResourcePropertyName( resourceDataProperty, null );
  }
  
}
