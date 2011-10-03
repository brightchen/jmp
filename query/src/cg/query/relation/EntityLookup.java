package cg.query.relation;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import org.reflections.Reflections;

import cg.common.reflect.ReflectionsBuilder;

public class EntityLookup
{
  private static EntityLookup defaultInstance;
  
  public static EntityLookup defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( EntityConnectorsAnnotationResolver.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new EntityLookup();
        }
      }
    }
    return defaultInstance;
  }

  private EntityLookup(){}
  
  public Set< Class > getAllEntityClasses()
  {
    Reflections reflections = ReflectionsBuilder.defaultInstance().buildAnnotationReflections();
    Set< Class > entities = new HashSet< Class >();
    Set< Class<?> > entityClasses = reflections.getTypesAnnotatedWith( Entity.class );
    for( Class<?> entityClass : entityClasses )
    {
      entities.add( entityClass );
    }
    return entities;
  }
}
