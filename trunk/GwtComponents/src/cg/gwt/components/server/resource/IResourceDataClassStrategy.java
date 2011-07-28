package cg.gwt.components.server.resource;

import cg.gwt.components.shared.data.ResourceData;
import cg.gwt.components.shared.data.UIContentData;

/*
 * this interface declare the interface of 
 */
public interface IResourceDataClassStrategy
{
  /*
   * get the real class of resource data of the contentData
   */
  public Class< ? extends ResourceData > getResourceDataClass( UIContentData contentData );

}
