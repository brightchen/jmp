package cg.gwt.components.server.resource;

import cg.common.util.ReflectionUtil;
import cg.gwt.components.shared.data.ResourceData;
import cg.gwt.components.shared.data.UIContentData;

/*
 * get the class of resource data from the generic definition
 */
public class ResourceDataClassGenericStrategy implements IResourceDataClassStrategy
{
  public static final ResourceDataClassGenericStrategy defaultInstance = new ResourceDataClassGenericStrategy();

  @Override
  @SuppressWarnings( "rawtypes" ) 
  public Class< ? extends ResourceData > getResourceDataClass( UIContentData contentData )
  {
    return ReflectionUtil.getGenericActualTypeArgumentClass( contentData.getClass(), UIContentData.class, ResourceData.class );
  }

}
