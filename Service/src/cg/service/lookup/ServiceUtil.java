package cg.service.lookup;

import java.lang.reflect.Modifier;

public class ServiceUtil
{
  public static void ensureImplementor( Class<?> service, Class<?> implementor )
  {
    if( service == null || implementor == null )
      throw new IllegalArgumentException( "Either the service interface or implementor should not be null." );
    
    //the implementor should be a class which implements the service interface
    if( !service.isInterface() )
      throw new IllegalArgumentException( "the service interface must be an interface." );

    if( !service.isAssignableFrom( implementor ) )
      throw new IllegalArgumentException( "the service interface should be assignable from implementor." );
    
    int modifiers = implementor.getModifiers();
    if( Modifier.isInterface( modifiers ) )
      throw new IllegalArgumentException( "the implementor should not be an interface." );
    if( Modifier.isAbstract( modifiers ) )
      throw new IllegalArgumentException( "the implementor should not be abstract." );
    if( Modifier.isPrivate( modifiers ) )
      throw new IllegalArgumentException( "the implementor should not be private." );
  }
  
  public static boolean isImplementor( Class<?> service, Class<?> implementor )
  {
    if( service == null || implementor == null )
      throw new IllegalArgumentException( "Either the service interface or implementor should not be null." );
    
    //the implementor should be a class which implements the service interface
    if( !service.isInterface() )
      throw new IllegalArgumentException( "the service interface must be an interface." );

    if( !service.isAssignableFrom( implementor ) )
      return false;
    
    int modifiers = implementor.getModifiers();
    if( Modifier.isInterface( modifiers ) || Modifier.isAbstract( modifiers ) || Modifier.isPrivate( modifiers ) )
      return false;
    return true;
  }
}
