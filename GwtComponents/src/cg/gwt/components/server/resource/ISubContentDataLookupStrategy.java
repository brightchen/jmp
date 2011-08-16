package cg.gwt.components.server.resource;

import java.util.List;

/*
 * lookup the sub-content-datas in a composite content data
 */
public interface ISubContentDataLookupStrategy
{
  /*
   * get the sub-resource data context according to current resource-data-context
   */
  public List< ResourceDataContext > getSubResourceDataContexts( ResourceDataContext context );
}
