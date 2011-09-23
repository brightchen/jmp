package cg.model.util;

import java.util.List;

import cg.model.api.IEntity;
import cg.model.api.IReadableModelView;
import cg.model.api.IWritableModelView;

public class ViewUtil
{
  public static < E extends IEntity, V extends IReadableModelView< E > > V entityToReadableView( E entity, V view )
  {
    return null;
  }

  public static < E extends IEntity, V extends IWritableModelView< E > > V entityToWritableView( E entity, V view )
  {
    return null;
  }

  public static < E extends IEntity, V extends IReadableModelView< E > > List< V > entitiesToReadableViews( List<E> entities, Class< V > viewClass )
  {
    return null;
  }

  public static < E extends IEntity, V extends IWritableModelView< E > > List< V > entitiesToWritableViews( List<E> entities, Class< V > viewClass )
  {
    return null;
  }

}
