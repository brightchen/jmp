package cg.gwt.components.server.resource;

import java.util.Locale;

import cg.resourcemanagement.ResourceManager;

public class ResourceUtil
{
  public static String getResourceValue( Locale locale, String resourceKey  )
  {
    return ResourceManager.getInstance().getString( locale, resourceKey );
  }

}
