package cg.gwt.components.server.resource;

import java.util.Locale;
import java.util.Set;

import cg.common.property.ClassProperty;
import cg.common.property.ClassPropertyUtil;
import cg.gwt.components.shared.data.ResourceData;
import cg.gwt.components.shared.data.UIContentData;
import cg.resourcemanagement.ResourceKeyManager;

/*
 * the builder can be looked as a type of lookup strategy
 */
public class ResourceDataBuilder implements IResourceDataLookupStrategy
{

  @Override
  public < RD extends ResourceData > RD getResourceData( Locale locale, UIContentData contentData, Class< RD > resourceDataClass )
  {
    try
    {
      return buildResourceData( locale, contentData, resourceDataClass.newInstance() );
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
  public static  < RD extends ResourceData > RD buildResourceData( Locale locale, UIContentData contentData, RD resourceData )
  {
    if( resourceData == null )
      return null;
    
    Class< ? extends ResourceData > resourceDataClass = (Class< ? extends ResourceData >)resourceData.getClass();
    Set< ClassProperty > classProperties = ClassPropertyUtil.getClassProperties( resourceDataClass, ResourceData.class );
    for( ClassProperty classProperty : classProperties )
    {
      String resourceKey = ResourceKeyManager.defaultInstance.getResourceKey( classProperty, contentData.getClass(), resourceData.getClass() );
      String resourceValue = ResourceUtil.getResourceValue( locale, resourceKey );
      ResourceDataUtil.setResourceValue( resourceData, classProperty, resourceValue );
    }
    return resourceData;
  }
}
