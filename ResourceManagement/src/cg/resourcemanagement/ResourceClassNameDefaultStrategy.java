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
  public String getResourceClassName( ClassProperty resourceDataProperty, Class<?> ownerContentDataClass, Class<?> ownerResourceDataClass )
  {
    String ownerClassShortName = ownerResourceDataClass.getSimpleName();
    ownerClassShortName = ownerClassShortName.toLowerCase();
    ownerClassShortName = ownerClassShortName.endsWith( RESOURCE_CALSS_POSTFIX ) 
                        ? ownerClassShortName.substring( 0, ownerClassShortName.length() - RESOURCE_CALSS_POSTFIX.length() )
                        : ownerClassShortName;
    return ownerClassShortName;
  }

}
