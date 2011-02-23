package cg.studio.reflections;

import java.util.Set;

public class TestReflections
{
  public static void main( String[] argv )
  {
    Set< Class< ? extends IBase > > subClasses = SubTypeReflections.findSubTypes( IBase.class );
    if( subClasses == null || subClasses.isEmpty() )
    {
      System.out.println( "no sub classes" );
      return;
    }
    
    for( Class< ? extends IBase > clazz : subClasses )
    {
      System.out.println( clazz.getName() );
    }
  }
  
}
