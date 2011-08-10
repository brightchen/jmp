package cg.gwt.components.server.resource;

import cg.gwt.components.shared.data.ResourceData;
import cg.gwt.components.shared.data.UIContentData;

/*
 * get the actual type of resource data of a content data, it only take care of the resource data of this content data
 * it doesn't take care the resource of the sub-content-datas
 */
public interface IResourceDataClassStrategy
{
  /*
   * get the real class of resource data of the contentData
   */
  public Class< ? extends ResourceData > getResourceDataClass( UIContentData contentData );

}
