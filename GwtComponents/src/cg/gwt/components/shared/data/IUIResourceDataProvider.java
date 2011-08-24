package cg.gwt.components.shared.data;

import cg.contentdata.shared.ResourceData;

public interface IUIResourceDataProvider< RD extends ResourceData >
{
  public RD getResourceData();
}
