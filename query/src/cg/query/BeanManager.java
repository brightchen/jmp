package cg.query;

import java.util.Map;

import cg.query.relation.BeanRelationship;
import cg.query.relation.IBeanRelationshipResolver;

public class BeanManager
{
  private static BeanManager defaultInstance;
  
  private IBeanRelationshipResolver resolver;
  
  private BeanManager(){}
  
  public static BeanManager defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( BeanManager.class )
      {
        if( defaultInstance == null )
          defaultInstance = new BeanManager();
      }
    }
    return defaultInstance;
  }
  /*
   * the relationship between the entity classes
   * parameter: aliasBeanMap the map ( alias ==> bean class )
   * for example role.account = account.id and account.user = user.id
   * 
   * it is possible that one bean class has multi aliases
   * for example: one user can have multiple address ( living address, mail address )
   */
  public BeanRelationship resolveRelationship( Map< String, Class<?> > aliasBeanMap )
  {
    
  }
}
