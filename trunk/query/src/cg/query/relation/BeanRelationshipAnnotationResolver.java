package cg.query.relation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * resolve the relationship by bean annotation
 */
public class BeanRelationshipAnnotationResolver implements IBeanRelationshipResolver
{
  private static BeanRelationshipAnnotationResolver defaultInstance;
  
  public static BeanRelationshipAnnotationResolver defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( BeanRelationshipAnnotationResolver.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new BeanRelationshipAnnotationResolver();
        }
      }
    }
    return defaultInstance;
  }

  private BeanRelationshipAnnotationResolver(){}
  

  @Override
  public BeanRelationship resolveRelationship( Map< String, Class< ? >> aliasBeanMap )
  {
    //build the graphic
    //when resolve the relationship via annotation, we hard to tell the bean with different alias ( living/mail address )
    Set< Class<?> > beanClasses = new HashSet< Class<?> >();
    for( Map.Entry< String, Class<?> > entry : aliasBeanMap.entrySet() )
    {
      beanClasses.add( entry.getValue() );
    }
    
    for( Class<?> beanClass : beanClasses )
    {
      //check all the relationship annotations
    }
    //then get the best line from the graphic
  }

}
