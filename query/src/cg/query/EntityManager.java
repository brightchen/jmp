package cg.query;

import java.util.Map;

import cg.query.relation.EntityNetwork;
import cg.query.relation.EntityRelationship;
import cg.query.relation.AnnotationEntityNetworkBuilder;
import cg.query.relation.IEntityRelationshipResolver;

public class EntityManager
{
  private static EntityManager defaultInstance;

  public static EntityManager defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( EntityManager.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new EntityManager();
        }
      }
    }
    return defaultInstance;
  }

  private EntityManager(){}
  
  /*
   * the relationship between the entity classes
   * parameter: aliasBeanMap the map ( alias ==> bean class )
   * for example role.account = account.id and account.user = user.id
   * 
   * it is possible that one bean class has multi aliases
   * for example: one user can have multiple address ( living address, mail address )
   */
  public EntityRelationship resolveRelationship( Map< String, Class<?> > aliasEntityMap )
  {
    return EntityNetwork.instance().resolveRelationship( aliasEntityMap );
  }
}
