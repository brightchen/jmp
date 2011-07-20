package cg.gwt.components.server.resource;

import java.lang.reflect.Method;

import cg.common.property.ClassProperty;
import cg.common.util.ReflectionUtil;
import cg.gwt.components.shared.data.ResourceData;

public class ResourceDataUtil
{
  
  /*
   * set the resource value to the ResourceData according to the class property
   */
  public static < RD extends ResourceData > void setResourceValue( RD resourceData, ClassProperty classProperty, String resourceValue )
  {
    Method setterMethod = ReflectionUtil.getMethod( resourceData.getClass(), classProperty.getSetterMethodName(), new Class<?>[]{ String.class } );
    try
    {
      setterMethod.invoke( resourceData, resourceValue );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      //it should treat as RuntimeException
    }
  }

}
