package cg.resourcemanagement;

import cg.common.property.ClassProperty;

/*
 * statically transform the resourceDataProperty to the resource key
 */
public class ResourcePropertyKeyDefaultLookupStrategy implements IResourcePropertyKeyLookupStrategy
{
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
  public ResourceKey getResourceKey( ClassProperty resourceDataProperty, ResourcePropertyContext context )
  {
    return new ResourceKey( getResourceModuleName( resourceDataProperty, context ),
                            getResourceClassName( resourceDataProperty, context ),
                            getResourcePropertyName( resourceDataProperty ) );
  }

  protected String getResourceModuleName( ClassProperty resourceDataProperty, ResourcePropertyContext context )
  {
    return resourceModuleNameStrategy.getResourceModuleName( null, context );
  }
  
  protected String getResourceClassName( ClassProperty resourceDataProperty, ResourcePropertyContext context )
  {
    return resourceClassNameStrategy.getResourceClassName( resourceDataProperty, context );
  }
  
  protected String getResourcePropertyName( ClassProperty resourceDataProperty )
  {
    return resourcePropertyNameStrategy.getResourcePropertyName( resourceDataProperty, null );
  }
  
}
