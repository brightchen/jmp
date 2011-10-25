package cg.model.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cg.model.api.IEntity;
import cg.model.api.IReadableModelView;
import cg.model.api.IWritableModelView;

public class ViewUtil
{
  public static < E extends IEntity, V extends IReadableModelView< E > > V entityToReadableView( E entity, V view )
  {
    view.getValuesFromEntity();
    return view;
  }

  public static < E extends IEntity, V extends IWritableModelView< E > > V entityToWritableView( E entity, V view )
  {
    return null;
  }

  public static < E extends IEntity, V extends IReadableModelView< E > > List< V > entitiesToReadableViews( List<E> entities, Class< V > viewClass )
  {
    if( entities == null || entities.isEmpty() )
      return Collections.emptyList();
    List<V> views = new ArrayList< V >();

    for( E entity : entities )
    {
      V view = null;
      try
      {
        view = viewClass.newInstance();
      }
      catch( Exception e )
      {
        throw new RuntimeException( "the " + viewClass.getName() + " not support default constructor." );
      }
      views.add( entityToReadableView( entity, view ) );
    }
    return views;
  }

  public static < E extends IEntity, V extends IWritableModelView< E > > List< V > entitiesToWritableViews( List<E> entities, Class< V > viewClass )
  {
    return null;
  }

}
