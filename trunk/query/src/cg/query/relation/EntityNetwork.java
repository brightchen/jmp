package cg.query.relation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cg.common.property.ClassProperty;

/*
 * BeanNetwork keeps all the relationship among beans
 */
@SuppressWarnings( "rawtypes" )
public class EntityNetwork
{
  private static EntityNetwork instance;
  
  private IEntityRelationshipResolver entityRelationshipResolver;
  
//  private Set< Class<?> > entities = new HashSet< Class<?> >();   // all the entities in this network
//  private List< EntityConnector > network = new ArrayList< EntityConnector >();
  private Map< Class, Set< EntityConnector > > network = new HashMap< Class, Set< EntityConnector > >();
  
  public static EntityNetwork instance()
  {
    if( instance == null )
    {
      synchronized( EntityNetwork.class )
      {
        if( instance == null )
        {
          instance = new EntityNetwork();
          instance.entityRelationshipResolver = EntityRelationshipAnnotationResolver.defaultInstance();
        }
      }
    }
    return instance;
  }
  
  private List< EntityConnector > connectors = new ArrayList< EntityConnector >();

  public void addConnector( EntityConnector connector )
  {
    connectors.add( connector );
  }

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
  public EntityRelationship resolveRelationship( Set< Class > entitiesToResolve )
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
    
    return findShortestRoute( entitiesToResolve );
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
   * precondition: all the entity of entitiesToResolve already merged into the network
   */
  protected EntityRelationship findShortestRoute( Set< Class > entitiesToResolve )
  {
    Set< Class > 
    
  }
}
