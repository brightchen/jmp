package cg.common.util;

import java.lang.reflect.Method;

public class EntityUtil
{
  /**
   * copy the entity data from source to destination
   * precondition: the source object and destination object have the same getter/setter method 
   *
   * @param source: the entity which the data copy from
   * @param dest: the entity which the data copy to
   */
  public static void shallowCopyEntity( Object source, Object dest )
  {
    if( source == null || dest == null )
      return;
    
    Class<?> sourceClass = source.getClass();
    Method[] sourceMethods = sourceClass.getMethods();
    
    Class<?> destClass = dest.getClass();

    for( Method method : sourceMethods )
    {
      if( !ReflectionUtil.isTypicalGetterMethod( method ) )
        continue;
      String setMethodName = ReflectionUtil.getCorrespondingSetMethodName( method.getName() );
      try
      {
        Object data = method.invoke( source, (Object[])null );
        Method setMethod = ReflectionUtil.getMethod( destClass, setMethodName, ReflectionUtil.getParameterTypes( new Object[]{ data } ) ); //destClass.getMethod( setMethodName, (Class<?>[])null );
        if( setMethod == null )
          continue;
        setMethod.invoke( dest, data );
      }
      catch( Exception e )
      {
        e.printStackTrace();
      }      
    }
  }
  
  
  /**
   *
   * @param entity
   * @return: the property style entity name( first character small case )
   */
  @SuppressWarnings( "rawtypes" )
  public static String getPropertyStyleName( Class entity )
  {
    String simpleName = entity.getSimpleName();
    return simpleName.substring( 0, 1 ).toLowerCase() + simpleName.substring( 1 );
  }

}
