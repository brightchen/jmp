package cg.contentdata.management;

import java.util.Locale;
import java.util.Set;

import cg.common.property.ClassProperty;
import cg.common.property.ClassPropertyUtil;
import cg.contentdata.shared.ResourceData;
import cg.resourcemanagement.ResourceKey;
import cg.resourcemanagement.ResourceKeyManager;
import cg.resourcemanagement.ResourcePropertyContext;

/*
 * the builder can be looked as a type of lookup strategy
 */
public class ResourceDataBuilder implements IResourceDataLookupStrategy
{

  @Override
  public < RD extends ResourceData > RD getResourceData( Locale locale, Class< RD > resourceDataClass, ResourceDataContext context )
  {
    try
    {
      return buildResourceData( locale, resourceDataClass.newInstance(), context );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      return null;
    }
  }

  /*
   * the ResourceData should only include the information of resource data, we can get all the properties of the resource data,
   * and then get the resource key from resource data class properties, and then get the resource value from resource key
   * 
   * ResourceData class ==> resource data class properties ==> resource keys ==> resource values
   */
  public static  < RD extends ResourceData > RD buildResourceData( Locale locale, RD resourceData, ResourceDataContext context )
  {
    if( resourceData == null )
      return null;
    
    Class< ? extends ResourceData > resourceDataClass = (Class< ? extends ResourceData >)resourceData.getClass();
    Set< ClassProperty > classProperties = ClassPropertyUtil.getClassProperties( resourceDataClass, ResourceData.class );
    for( ClassProperty classProperty : classProperties )
    {
      ResourcePropertyContext propertyContext = new ResourcePropertyContext( context.getOwnerContentData().getClass(), 
                                                                             context.getResourceKey(), resourceData.getClass() );
      ResourceKey resourceKey = ResourceKeyManager.defaultInstance.getResourceKey( classProperty, propertyContext );
      String resourceValue = ResourceUtil.getResourceValue( locale, resourceKey.getKey() );
      ResourceDataUtil.setResourceValue( resourceData, classProperty, resourceValue );
    }
    return resourceData;
  }
}
