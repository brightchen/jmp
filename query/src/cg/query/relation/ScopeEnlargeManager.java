package cg.query.relation;

import java.util.HashSet;
import java.util.Set;

import cg.common.util.CollectionUtil;

/**
 * this class manages the scope enlarge for cg.query.relation.EntityNetwork.resolveNetworkByEnlargeScope(EntityNetwork, Set<Class>, IEntityConnectorsResolver)
 * @author bchen
 *
 */
@SuppressWarnings( "rawtypes" )
public class ScopeEnlargeManager
{
  private static ScopeEnlargeManager defaultInstance;
  
  public static ScopeEnlargeManager defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( ScopeEnlargeManager.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new ScopeEnlargeManager();
        }
      }
    }
    return defaultInstance;
  }
  
  private ScopeEnlargeManager(){}
  
  /**
   * enlarge the scope ( resolvedNetwork or resolvingEntities )
   * @param resolvedNetwork
   * @param resolvingEntities
   * @param connectorsResolver
   * @return the entity which added to enlarge the scope
   */
  public Class enlargeScope( EntityNetwork resolvedNetwork, Set< Class > resolvingEntities, IEntityConnectorsResolver connectorsResolver )
  {
    Set< Class > entities = connectorsResolver.getAllEntities();
    if( resolvedNetwork != null )
      entities.removeAll( resolvedNetwork.getEntities() );
    if( resolvingEntities != null && !resolvingEntities.isEmpty() )
      entities.removeAll( resolvingEntities );
    
    if( entities.isEmpty() )
    {
      // can't enlarge the scope
      return null;  
    }

    //first, pick one which can directly add to the resolvedNetwork
    if( resolvedNetwork != null )
    {
      for( Class entity : entities )
      {
        if( resolvedNetwork.addDirectlyConnectedEntity( entity, connectorsResolver ) )
          return entity;
      }
    }
      
    //second, pick one which can directly add to any of the entities of resolvingEntities
    if( resolvingEntities != null )
    {
      for( Class entity : entities )
      {
        Set< EntityConnector > connectors = connectorsResolver.getDirectConnectors( entity );
        if( connectors == null || connectors.isEmpty() )
          continue;
        
        Set< Class > intersectionEntities = CollectionUtil.shallowCloneTo( resolvingEntities, new HashSet<Class>() );
        intersectionEntities.retainAll( EntityRelationUtil.getAllEntities( connectors ) );
        if( !intersectionEntities.isEmpty() )
          return entity;
      }
    }
    
    return null;
  }
}
