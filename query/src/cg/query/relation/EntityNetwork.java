package cg.query.relation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cg.common.property.ClassProperty;

/*
 * EntityNetwork keeps the relationship among beans
 * it can be partial of WholeEntityNetwork
 */
@SuppressWarnings( "rawtypes" )
public class EntityNetwork
{
  private IEntityRelationshipResolver entityRelationshipResolver;
  
  protected Map< Class, Set< EntityConnector > > network = new HashMap< Class, Set< EntityConnector > >();

  /*
   * precondition: at least one entity of this connector is already inside the network
   */
  protected boolean addEntityConnector( EntityConnector connector )
  {
    Class[] entities = connector.getEntities();
    Set< Class > allEntities = getEntities();
    if( !allEntities.contains( entities[0] ) && !allEntities.contains( entities[0] ) )
    {
      //this connector should not be added into network as it will be orphan it added
      return false;
    }

    addEntityConnectorPart( connector, connector.getEntityProperty1() );
    addEntityConnectorPart( connector, connector.getEntityProperty2() );
    
    return true;
  }
  
  /*
   * precondition: at least one entity of this connector is already inside the network
   */
  private void addEntityConnectorPart( EntityConnector connector, ClassProperty classProperty )
  {
    Class entity = classProperty.getDeclaringClass();
    Set< EntityConnector > connectors = network.get( entity );
    if( connectors == null )
    {
      //this entity is not in the network, add it into the network
      Set< EntityConnector > conns = new HashSet< EntityConnector >();
      conns.add( connector );
      network.put( entity, conns );
    }
    else
    {
      connectors.add( connector );
    }
  }
  
  /*
   * add the entity to current network
   * Parameters:
   * entity - the entity going to add to this EntityNetwork 
   * containerNetwork - the network which contains entity and its relationship
   * 
   * return:
   * whether add entity successful
   */
  public boolean addDirectlyConnectedEntity( Class entity, EntityNetwork containerNetwork )
  {
    Map< Class, EntityConnector > connectedEntities = containerNetwork.getConnectedEntities( entity );
    //the entities of this network
    Set< Class > entities = network.keySet();
    entities.retainAll( connectedEntities.keySet() );
    if( entities.isEmpty() )    //this network don't have intersection with connectedEntities
      return false;
    
    //we only add the connectors which connect to the entities of network instead of all the connectors of this entity
    Set< EntityConnector > connectors = new HashSet< EntityConnector >();
    for( Class connectedEntity : entities )
    {
      connectors.add( connectedEntities.get( connectedEntity ) );
    }
    network.put( entity, connectors );
    return true;
  }
  
  /*
   * add the entity which directly connected network into it,
   * this method use entityRelationshipResolver to resolve the connectors of this entity 
   * return true if add entity into network successful
   */
  protected boolean addDirectlyConnectedEntity( Class entity )
  {
    Set< Class > entities = network.keySet();
    if( entities.contains( entity ) )
      return true;
    if( entities.isEmpty() )
    {
      // network was empty, simply add this entity, no network yet
      entities.add( entity );
      return true;
    }
    
    if( entityRelationshipResolver == null )
      return false;   //can't get the connectors;
    
    //check
    List< EntityConnector > allConnectors = entityRelationshipResolver.getDirectConnectors( entity );
    Set<Class> connectedEntities = EntityRelationUtil.getDirectConnectedEntities( entity, allConnectors );
    entities.retainAll( connectedEntities );
    if( entities.isEmpty() )
      return false;   //only the entities connected to this entity are not inside network;

    //we only add the connectors which connect to the entities of network instead of all the connectors of this entity
    Set< EntityConnector > connectors = new HashSet< EntityConnector >();
    for( EntityConnector connector : allConnectors )
    {
      Class anotherEntity = connector.getPropertyOfAnotherEntity( entity ).getDeclaringClass();
      if( entities.contains( anotherEntity ) )
        connectors.add( connector );
    }
    network.put( entity, connectors );
    return true;
  }
  
  /*
   * this interface is used when there are no same entity in the criteria
   */
  public EntityNetwork resolveRelationship( Set< Class > entitiesToResolve )
  {
    if( entitiesToResolve == null || entitiesToResolve.size() < 2 )
      return null;    //no relationship if less than 2 entities
    
    //check if all the entitiesToResolve already in this network, if not, we have to merge these entities into network
    Set< Class > entities = network.keySet();
    if( !entities.containsAll( entitiesToResolve ) )
    {
      // get the entities which haven't added to the network
      Set< Class > entitiesToAdd = new HashSet< Class >();
      entitiesToAdd.addAll( entities );
      entitiesToAdd.retainAll( entitiesToResolve );
      
      for( Class<?> entity : entitiesToAdd )
      {
        addDirectlyConnectedEntity( entity );
      }
    }
    
    return resolveNetwork( entitiesToResolve );
  }
  


  /*
   * precondition: all the entity of entitiesToResolve already merged into the network
   */
  protected EntityNetwork resolveNetwork( Set< Class > entitiesToResolve )
  {
    EntityNetwork resolvedNetwork = new EntityNetwork();
    Class solvingEntity = entitiesToResolve.iterator().next();
    resolvedNetwork.addDirectlyConnectedEntity( solvingEntity, this );
    entitiesToResolve.remove( solvingEntity );
    
    return resolveNetwork( resolvedNetwork, entitiesToResolve );
  }
  
  protected EntityNetwork resolveNetwork( EntityNetwork resolvedNetwork, Set< Class > entitiesToResolve )
  {
    if( entitiesToResolve.isEmpty() )
      return resolvedNetwork;
    
    //find the direct connected entities and add them into resolvedNetwork
    if( resolveDirectConnectedEntities( resolvedNetwork, entitiesToResolve ) )
      return resolvedNetwork;
    
    // introduce a bridge entity from this network to resolve the entities
    if( resolveBridgeConnectedEntities( resolvedNetwork, entitiesToResolve ) )
      return resolvedNetwork;
    
    // add any of the remained entity which connected to any of entity of resolved and resolve
    if( resolveNetworkByEnlargeIt( resolvedNetwork, entitiesToResolve ) )
      return resolvedNetwork;

    throw new EntityNetworkResolvingException();
  }
  
  /*
   * this method resolve the entities by put the entities which directly connected to the resolvedNetwork
   * return whether the entitiesToResolve being completely resolved
   */
  protected boolean resolveDirectConnectedEntities( EntityNetwork resolvedNetwork, Set< Class > entitiesToResolve )
  {
    if( entitiesToResolve == null || entitiesToResolve.isEmpty() )
      return true;
  
    int numOfResolvedEntity;
    do
    {
      Set< Class > thisResolvedEntities = new HashSet< Class >();  //the entities resolved this run
      for( Class resolvingEntity : entitiesToResolve )
      {
        boolean added = resolvedNetwork.addDirectlyConnectedEntity( resolvingEntity, this );
        if( added )
        {
          thisResolvedEntities.add( resolvingEntity );
        }
      }
      numOfResolvedEntity = thisResolvedEntities.size();
      if( numOfResolvedEntity > 0 )
        entitiesToResolve.removeAll( thisResolvedEntities );
    }while( numOfResolvedEntity > 0 );
    
    return entitiesToResolve.isEmpty();
  }
  
  /*
   * in this step, all the entities of entitiesToResolve are not directly connected to resolvedNetwork
   * we should try to find a bridge entity from this EntityNetwork to connect both resolvedNetwork and entitiesToResolve
   */
  protected boolean resolveBridgeConnectedEntities( EntityNetwork resolvedNetwork, Set< Class > entitiesToResolve )
  {
    if( entitiesToResolve.isEmpty() )
      return true;
    
    // get the entity from the network which doesn't belong to resolvedEntities/entitiesToResolve, 
    // and which connect to both resolvedEntities and entitiesToResolve
    Set< Class > otherEntities = new HashSet< Class >();  // the entities which not belongs to resolvedEntities/entitiesToResolve
    otherEntities = network.keySet();
    otherEntities.removeAll( resolvedNetwork.getEntities() );
    otherEntities.removeAll( entitiesToResolve );
    if( otherEntities.isEmpty() )
    {
      throw new RuntimeException( "the entities can't be resolved" );
    }
    
    int numOfResolvedEntity;
    do
    {
      numOfResolvedEntity = 0;
      for( Class entity : otherEntities )
      {
        //check whether the entity connected to both resolvedNetwork and entitiesToResolve
        //namely, the entity's connected entities has intersection with both resolvedNetwork and entitiesToResolve
        Map< Class, EntityConnector > connectedEntites = getConnectedEntities( entity );

        Set< Class > resolvedEntitiesCopy = new HashSet< Class >();
        resolvedEntitiesCopy.addAll( resolvedNetwork.getEntities() );
        Set< Class > entitiesToResolveCopy = new HashSet< Class >();
        entitiesToResolveCopy.addAll( entitiesToResolve );
        
        resolvedEntitiesCopy.retainAll( connectedEntites.keySet() );
        if( resolvedEntitiesCopy.isEmpty() )
        {
          continue;   // this entity doesn't connect to any of entity of resolvedEntities
        }
        entitiesToResolveCopy.retainAll( connectedEntites.keySet() );
        if( entitiesToResolveCopy.isEmpty() )
        {
          continue;  // this entity doesn't connect to any of entity of entitiesToResolveCopy
        }
        
        //this entity is a bridge entity, add this entity and all the entities of entitiesToResolve which connected to this entity
        resolvedNetwork.addDirectlyConnectedEntity( entity, this );
        for( Class entityToResolve : entitiesToResolveCopy )
        {
          resolvedNetwork.addDirectlyConnectedEntity( entityToResolve, this );
        }

        entitiesToResolve.removeAll( entitiesToResolveCopy );

        if( entitiesToResolve.isEmpty() )
        {
          // all the entities of entitiesToResolve have been resolved.
          return true;
        }

        ++numOfResolvedEntity;
      }
    }
    while( numOfResolvedEntity > 0 );
    
    return false;
  }
  
  /*
   * resolve connected entities by add more entities into the resolvedNetwork.
   * this method should be called after directly resolving and bridge resolving
   * so, add only one entity to resolvedNetwork can not be resolved by directly resolving
   *   ( if can, it can be resolved by bridge resolving without adding entity to resolvedNetwork
   */
  protected boolean resolveNetworkByEnlargeIt( EntityNetwork resolvedNetwork, Set< Class > entitiesToResolve )
  {
    //pick any entity from this network which connected to resolvedNetwork and add to it
    Set< Class > otherEntities = new HashSet< Class >();  // the entities which not belongs to resolvedEntities/entitiesToResolve
    otherEntities = network.keySet();
    otherEntities.removeAll( resolvedNetwork.getEntities() );
    otherEntities.removeAll( entitiesToResolve );
    if( otherEntities.isEmpty() )
    {
      throw new RuntimeException( "the entities can't be resolved" );
    }

    for( Class otherEntity : otherEntities )
    {
      if( resolvedNetwork.addDirectlyConnectedEntity( otherEntity, this ) )
        break;
    }
    
    if( resolveBridgeConnectedEntities( resolvedNetwork, entitiesToResolve ) )
      return true;
    
    return resolveNetworkByEnlargeIt( resolvedNetwork, entitiesToResolve );
  }
  
  protected  Map< Class, EntityConnector > getConnectedEntities( Class entity )
  {
    Set< EntityConnector > connectors = network.get( entity );
    Map< Class, EntityConnector > connectedEntities = new HashMap< Class, EntityConnector >();
    for( EntityConnector connector : connectors )
    {
      connectedEntities.put( connector.getPropertyOfAnotherEntity( entity ).getDeclaringClass(), connector );
    }
    return connectedEntities;
  }
  
  public Set< Class > getEntities()
  {
    return network.keySet();
  }
}
