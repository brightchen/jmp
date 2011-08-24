package cg.contentdata.management;

import java.lang.reflect.Method;

import cg.common.property.ClassProperty;
import cg.common.property.ClassPropertyUtil;
import cg.common.util.ReflectionUtil;
import cg.contentdata.shared.ResourceData;

public class ResourceDataUtil
{
  
  /*
   * set the resource value to the ResourceData according to the class property
   */
  public static < RD extends ResourceData > void setResourceValue( RD resourceData, ClassProperty classProperty, String resourceValue )
  {
    Method setterMethod = ReflectionUtil.getMethod( resourceData.getClass(), ClassPropertyUtil.getSetterName( classProperty.getName() ), new Class<?>[]{ Object.class } );
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
