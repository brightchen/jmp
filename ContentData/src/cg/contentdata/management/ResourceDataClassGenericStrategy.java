package cg.contentdata.management;

import cg.common.util.ReflectionUtil;
import cg.contentdata.shared.ResourceData;
import cg.contentdata.shared.UIContentData;

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
