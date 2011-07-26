package cg.gwt.components.server.resource;

import java.util.Locale;

import cg.gwt.components.shared.data.UIContentData;

public interface IResourceDataFillupStrategy
{
  /*
   * fill the resource data of the contentData.
   * this method only fill the resource data of current contentData, it doesn't fill the sub-contentDatas
   * create - should create the resource data if the resource data of contentData is null 
   */
  public boolean fillResourceData( Locale locale, UIContentData contentData, boolean create );

}
