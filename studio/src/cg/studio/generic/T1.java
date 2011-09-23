package cg.studio.generic;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

class A
{
  private List< String > list;
  
  private String name;
}

public class T1
{
  public static void main( String[] argvs )
  {
    try
    {
      {
        Field[] fields = A.class.getDeclaredFields();
        Field list = A.class.getDeclaredField( "list" );
  //      list.setAccessible( true );
        Class<?> type = list.getType();
        Type genericType = list.getGenericType();
        if( genericType instanceof ParameterizedType )
        {
          Type ownerType = ((ParameterizedType)genericType).getRawType();
          Type[] parameters = ((ParameterizedType)genericType).getActualTypeArguments();
        }
      }
      
      {
      Field[] fields = A.class.getDeclaredFields();
      Field list = A.class.getDeclaredField( "name" );
      list.setAccessible( true );
      Class<?> type = list.getType();
      Type genericType = list.getGenericType();
      if( genericType instanceof ParameterizedType )
      {
        Type ownerType = ((ParameterizedType)genericType).getOwnerType();
        Type[] parameters = ((ParameterizedType)genericType).getActualTypeArguments();
      }
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
}
