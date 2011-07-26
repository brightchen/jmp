package cg.gwt.components.api;

import cg.gwt.components.shared.data.ResourceData;

public @interface IResourceDataClass
{
  public Class<? extends ResourceData > resourceClassName();
}
