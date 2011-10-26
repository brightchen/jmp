package cg.model.util;

import java.lang.reflect.Method;

import cg.common.util.ReflectionUtil;
import cg.model.api.IEntity;
import cg.model.api.IReadableModelView;

public class ViewFactory
{
  public static < E extends IEntity, V extends IReadableModelView< E > > V createReadableView( E entity, Class<V> viewClass )
  {
    try
    {
      V view = viewClass.newInstance();
      setEntityToView( entity, view );
      return view;
    }
    catch( Exception e )
    {
      throw new RuntimeException( "the view class " + viewClass.getName() + " not support default constructor." );
    }
  }
  
  public static < E extends IEntity, V extends IReadableModelView< E > > void setEntityToView( E entity, V view )
  {
    try
    {
      Method setEntityMethod = ReflectionUtil.getMethod( view.getClass(), "setEntity", new Class<?>[]{ IEntity.class } );
      if( setEntityMethod != null )
        setEntityMethod.invoke( view, entity );
    }
    catch( Exception e )
    {
    }
  }
}
