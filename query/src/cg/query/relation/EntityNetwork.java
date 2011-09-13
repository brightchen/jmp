package cg.query.relation;

import java.util.ArrayList;
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
  
//  private Set< Class<?> > entities = new HashSet< Class<?> >();   // all the entities in this network
//  private List< EntityConnector > network = new ArrayList< EntityConnector >();
  protected Map< Class, Set< EntityConnector > > network = new HashMap< Class, Set< EntityConnector > >();
//  protected List< EntityConnector > connectors = new ArrayList< EntityConnector >();
//
//  public void addConnector( EntityConnector connector )
//  {
//    connectors.add( connector );
//  }

  /*
   * 1. try to find the shortest route which cover all the entities in the aliasEntityMap
   */
  public EntityRelationship resolveRelationship( Map< String, Class > aliasEntityMap )
  {
    return null;
  }
  
  /*
   * this interface is used when there are no same entity in the criteria
   */
  public EntityNetwork resolveRelationship( Set< Class > entitiesToResolve )
  {
    if( entitiesToResolve == null || entitiesToResolve.size() < 2 )
      return null;    //no relationship if less than 2 entities
    
    Set< Class > entities = network.keySet();
    if( !entities.containsAll( entitiesToResolve ) )
    {
      // get the entities which haven't added to the network
      Set< Class > entitiesToAdd = new HashSet< Class >();
      entitiesToAdd.addAll( entities );
      entitiesToAdd.retainAll( entitiesToResolve );
      
      for( Class<?> entity : entitiesToAdd )
      {
        mergeEntityToNetwork( entity );
      }
    }
    
    return findSmallestNetwork( entitiesToResolve );
  }
  
  protected void mergeEntityToNetwork( Class entity )
  {
    Set< Class > entities = network.keySet();
    if( entities.contains( entity ) )
      return;
    if( entities.isEmpty() )
    {
      // simple add this entity, no network yet
      entities.add( entity );
      return;
    }
    
    List< EntityConnector > connectors = entityRelationshipResolver.getDirectConnectors( entity );
    // check the connectors, if any of the entity of the opposite connectors belong the the network
    // then, this entity can add to the network
    for( EntityConnector connector : connectors )
    {
      ClassProperty otherProperty = connector.getPropertyOfOtherEntity( entity );
      Class<?> otherEntity = otherProperty.getDeclaringClass();
      if( entities.contains( otherEntity ) )
      {
        //found the connector of the entity to the network
        addEntityConnector( connector );
        return;
      }
    }
    
    // didn't found the direct connector between entity to the network,
    // try the indirect connection
  }
  
  protected void addEntityConnector( EntityConnector connector )
  {
    addEntityConnectorPart( connector, connector.getEntityProperty1() );
    addEntityConnectorPart( connector, connector.getEntityProperty2() );
  }
  
  protected void addEntityConnectorPart( EntityConnector connector, ClassProperty classProperty )
  {
    Class entity = classProperty.getDeclaringClass();
    Set< EntityConnector > connectors = network.get( entity );
    if( connectors == null )
    {
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
  public boolean addEntity( Class entity, EntityNetwork containerNetwork )
  {
    Map< Class, EntityConnector > connectedEntities = containerNetwork.getConnectedEntities( entity );
    //the entities of this network
    Set< Class > entities = network.keySet();
    entities.retainAll( connectedEntities.keySet() );
    if( entities.isEmpty() )    //this network don't have intersection with connectedEntities
      return false;
    Set< EntityConnector > connectors = new HashSet< EntityConnector >();
    for( Class connectedEntity : entities )
    {
      connectors.add( connectedEntities.get( connectedEntity ) );
    }
    network.put( entity, connectors );
    return true;
  }
  
  /*
   * precondition: all the entity of entitiesToResolve already merged into the network
   */
  protected EntityNetwork findSmallestNetwork( Set< Class > entitiesToResolve )
  {
    Set< Class > resolvedEntities = new HashSet< Class >();
    Class solvingEntity = entitiesToResolve.iterator().next();
    resolvedEntities.add( solvingEntity );
    entitiesToResolve.remove( solvingEntity );
    
    return findSmallestNetwork( resolvedEntities, entitiesToResolve );
  }
  
  protected EntityNetwork findSmallestNetwork( EntityNetwork resolvedNetwork, Set< Class > entitiesToResolve )
  {
    if( entitiesToResolve.isEmpty() )
      return resolvedNetwork;
    
    /////////////////////////////////////////////////////////////////////////////////
    //find the direct connected entities and add them into resolvedNetwork
    /////////////////////////////////////////////////////////////////////////////////
    if( resolveDirectConnectedEntities( resolvedNetwork, entitiesToResolve ) )
      return resolvedNetwork;
    
    /////////////////////////////////////////////////////////////////////////////////
    // get the  connection ( second level ) by add other entity of network
    /////////////////////////////////////////////////////////////////////////////////
    if( resolveBridgeConnectedEntities( resolvedNetwork, entitiesToResolve ) )
      return resolvedNetwork;

    // get the entity from the network which doesn't belong to resolvedEntities/entitiesToResolve, 
    // and which connect to both resolvedEntities and entitiesToResolve
    Set< Class > otherEntities = new HashSet< Class >();  // the entities which not belongs to resolvedEntities/entitiesToResolve
    otherEntities = network.keySet();
    otherEntities.removeAll( resolvedEntities );
    otherEntities.removeAll( entitiesToResolve );
    if( otherEntities.isEmpty() )
    {
      //
      throw new RuntimeException( "the route can't be found" );
    }
    
    do
    {
      numOfResolvedEntity = 0;
      for( Class entity : otherEntities )
      {
        Map< Class, EntityConnector > connectedEntites = getConnectedEntities( entity );

        Set< Class > resolvedEntitiesCopy = new HashSet< Class >();
        resolvedEntitiesCopy.addAll( resolvedEntities );
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
        
        resolvedEntities.add( entity );
        resolvedEntities.addAll( entitiesToResolveCopy );
        entitiesToResolve.removeAll( entitiesToResolveCopy );
        if( entitiesToResolve.isEmpty() )
        {
          // all the entities of entitiesToResolve have been resolved.
          return resolvedEntities;
        }

        ++numOfResolvedEntity;
      }
    }
    while( numOfResolvedEntity > 0 );
    
    /////////////////////////////////////////////////////////////////////////////////
    // add any of the remained entity which connected to any of entity of resolved and resolve
    /////////////////////////////////////////////////////////////////////////////////

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
        boolean added = resolvedNetwork.addEntity( resolvingEntity, this );
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
    
    ///+++++++++++++++++
    do
    {
      numOfResolvedEntity = 0;
      for( Class entity : otherEntities )
      {
        Map< Class, EntityConnector > connectedEntites = getConnectedEntities( entity );

        Set< Class > resolvedEntitiesCopy = new HashSet< Class >();
        resolvedEntitiesCopy.addAll( resolvedEntities );
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
        
        resolvedEntities.add( entity );
        resolvedEntities.addAll( entitiesToResolveCopy );
        entitiesToResolve.removeAll( entitiesToResolveCopy );
        if( entitiesToResolve.isEmpty() )
        {
          // all the entities of entitiesToResolve have been resolved.
          return resolvedEntities;
        }

        ++numOfResolvedEntity;
      }
    }
    while( numOfResolvedEntity > 0 );
  }
  
  protected  Map< Class, EntityConnector > getConnectedEntities( Class entity )
  {
    Set< EntityConnector > connectors = network.get( entity );
    Map< Class, EntityConnector > connectedEntities = new HashMap< Class, EntityConnector >();
    for( EntityConnector connector : connectors )
    {
      connectedEntities.put( connector.getPropertyOfOtherEntity( entity ).getDeclaringClass(), connector );
    }
    return connectedEntities;
  }
  
  public Set< Class > getEntities()
  {
    return network.keySet();
  }
}
