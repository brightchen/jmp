package cg.gwt.components.server.resource;

import cg.gwt.components.annotation.IResourceDataClass;
import cg.gwt.components.shared.data.ResourceData;
import cg.gwt.components.shared.data.UIContentData;

/*
 * get the class of resource data from the annotation
 */
public class ResourceDataClassAnnotationStrategy implements IResourceDataClassStrategy
{
  public static final ResourceDataClassAnnotationStrategy defaultInstance = new ResourceDataClassAnnotationStrategy();
  
  @Override
  @SuppressWarnings( "rawtypes" ) 
  public Class< ? extends ResourceData > getResourceDataClass( UIContentData contentData )
  {
    IResourceDataClass resourceDataClass = contentData.getClass().getAnnotation( IResourceDataClass.class );
    if( resourceDataClass == null )
      return null;
    return resourceDataClass.resourceDataClass();
  }

}
