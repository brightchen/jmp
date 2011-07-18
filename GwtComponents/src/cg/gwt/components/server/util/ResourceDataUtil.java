package cg.gwt.components.server.util;

import java.lang.reflect.Method;

import cg.common.property.ClassProperty;
import cg.common.util.ReflectionUtil;
import cg.gwt.components.shared.data.IUIResourceData;

public class ResourceDataUtil
{
  
  /*
   * set the resource value to the ResourceData according to the class property
   */
  public static < RD extends IUIResourceData > void setResourceValue( RD resourceData, ClassProperty classProperty, String resourceValue )
  {
    Method setterMethod = ReflectionUtil.getMethod( resourceData.getClass(), classProperty.getSetterMethodName(), new Object[]{ String.class } );
    try
    {
      setterMethod.invoke( resourceData, resourceValue );
    }
    catch( Exception e )
    {
      //it should treat as RuntimeException
    }
  }

}
