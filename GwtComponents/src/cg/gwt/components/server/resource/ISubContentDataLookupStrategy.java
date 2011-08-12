package cg.gwt.components.server.resource;

import java.util.List;

import cg.gwt.components.shared.data.UIContentData;

/*
 * lookup the sub-content-datas in a composite content data
 */
public interface ISubContentDataLookupStrategy
{
  @SuppressWarnings( "rawtypes" ) 
  public List< UIContentData > getSubContentData( UIContentData contentData );
}
