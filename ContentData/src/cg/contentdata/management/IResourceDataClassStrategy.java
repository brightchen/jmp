package cg.contentdata.management;

import cg.contentdata.shared.ResourceData;
import cg.contentdata.shared.UIContentData;

/*
 * get the actual type of resource data of a content data, it only take care of the resource data of this content data
 * it doesn't take care the resource of the sub-content-datas
 */
public interface IResourceDataClassStrategy
{
  /*
   * get the real class of resource data of the contentData
   */
  @SuppressWarnings( "rawtypes" ) 
  public Class< ? extends ResourceData > getResourceDataClass( UIContentData contentData );

}
