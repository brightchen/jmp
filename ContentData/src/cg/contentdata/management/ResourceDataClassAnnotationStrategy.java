package cg.contentdata.management;

import cg.contentdata.annotation.IResourceDataClass;
import cg.contentdata.shared.ResourceData;
import cg.contentdata.shared.UIContentData;


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
