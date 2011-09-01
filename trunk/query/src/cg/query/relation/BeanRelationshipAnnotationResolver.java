package cg.query.relation;

import java.util.Map;

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
    //then get the best line from the graphic
  }

}
