package cg.studio.reflections;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MethodTester
{
  public static void main( String[] argvs )
  {
    Set< Method > allMethods = new HashSet< Method >();
    //the sub class's method override the super class's method, but they are different methods for Set
    allMethods.addAll( Arrays.asList( Base.class.getDeclaredMethods() ) );
    allMethods.addAll( Arrays.asList( Sub.class.getDeclaredMethods() ) );
    System.out.println( allMethods );
  }
}
