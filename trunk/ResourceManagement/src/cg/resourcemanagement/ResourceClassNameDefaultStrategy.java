package cg.resourcemanagement;

import cg.common.property.ClassProperty;

/*
 * get the resource class name by the resource owner class's name
 */
public class ResourceClassNameDefaultStrategy implements IResourceClassNameStrategy
{
  public static final ResourceClassNameDefaultStrategy defaultInstance = new ResourceClassNameDefaultStrategy();
  
  private final String RESOURCE_CALSS_POSTFIX = "resourcedata";

  @Override
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

}
