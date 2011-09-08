package cg.studio.generic;

import java.lang.reflect.Field;
import java.lang.reflect.TypeVariable;
import java.util.List;

class A
{
  private List< String > l;
}

public class T1
{
  public static void main( String[] argvs )
  {
    try
    {
      Field l = A.class.getField( "l" );
      Class<?> type = l.getType();
      TypeVariable<Class<?>>[] variables = type.getTypeParameters();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
}
