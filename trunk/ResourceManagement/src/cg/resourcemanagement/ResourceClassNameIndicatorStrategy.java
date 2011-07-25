package cg.resourcemanagement;

import cg.common.property.ClassProperty;
import cg.resourcemanagement.api.IResourceClassIndicator;

public class ResourceClassNameIndicatorStrategy implements IResourceClassNameStrategy
{
  public static final ResourceClassNameIndicatorStrategy defaultInstance = new ResourceClassNameIndicatorStrategy();
  /*
   * get the resource class name from IResourceClassIndicator if the resourceOwnerClass is implemented IResourceClassIndicator
   * @see cg.resourcemanagement.IResourceClassNameStrategy#getResourceClassName(cg.common.property.ClassProperty, java.lang.Class)
   */
  @Override
  public String getResourceClassName( ClassProperty resourceDataProperty, Class< ? > resourceOwnerClass )
  {
    if( !IResourceClassIndicator.class.isAssignableFrom( resourceOwnerClass ) )
      return null;
    try
    {
      return ( (IResourceClassIndicator)resourceOwnerClass.newInstance() ).getResourceClassName();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
    return null;
  }

}
