package cg.resourcemanagement;

import cg.common.property.ClassProperty;

/*
 * statically transform the resourceDataProperty to the resource key
 */
public class ResourcePropertyKeyDefaultLookupStrategy implements IResourcePropertyKeyLookupStrategy
{
  private final String RESOURCE_CALSS_POSTFIX = "resourcedata";
  private final String SEPERATOR = ".";

  private String modulePrefix = "um" + SEPERATOR;

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
    return modulePrefix + ownerClassShortName + SEPERATOR + resourceDataProperty.getName().toLowerCase();
  }

}
