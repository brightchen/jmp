package cg.query.relation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
 * WholeEntityNetwork is an EntityNetwork which includes all connected entities at a certain time.
 * namely, the WholeEntityNetwork can't be and more entity into it.
 * A project may have multiple separated WholeEntityNetwork
 * 
 * this network will add all the connected entities into the network when adding an entity into it
 */
public class WholeEntityNetwork extends EntityNetwork
{
  
  /**
   * build a network which covers all the entities.
   * the built network will be added into this manager
   * @param entities
   * @return: the built network 
   */
  @Override
  public boolean buildEntityNetwork( Set< Class > entities )
  {
    if( !super.buildEntityNetwork( entities ) )
      return false;
    
    // handle the connectors which only one entity added into the WholeEntityNetwork.
    return completeWholeEntityNetwork();
  }

  protected boolean completeWholeEntityNetwork()
  {
    return true;
  }
  

  /**
   * add the entity which directly connected to this network into it.
   * this method will check if the entity directly connected to the network, 
   * do nothing and return false if entity doesn't directly connected to the network
   * 
   * @param entity the entity going to add to this EntityNetwork 
   * @param containerNetwork the network which contains entity and its connectors, we can get this entity's connectors from containerNetwork
   * @return return true if add entity successful
   */
  @Override
  public boolean addDirectlyConnectedEntity( Class entity, IEntityConnectorsResolver connectorsResolver )
  {
    //the entity simply added to the network when network was empty or the network already contain this entity.
    //we didn't get the connectors of this entity when connectorsResolver is EntityConnectorsAnnotationResolver
    Set< Class > networkEntities = network.keySet();
    if( networkEntities.contains( entity ) )
      return true;

    if( connectorsResolver == null )
      return false;
    
    //call connectorsResolver.getDirectConnectors even if network was empty to notify the connectorsResolver this entity
    Set< EntityConnector > entityConnectors = connectorsResolver.getDirectConnectors(  entity );
    Set< EntityConnector > connectors;
    if( networkEntities.isEmpty() || entityConnectors.isEmpty() )
    {
      connectors = Collections.emptySet();
      network.put( entity, connectors );
      return true;
    }

    boolean isEntityDirectlyConnectToNetwork = false;
    // check if the entity directly connect to network;
    for( EntityConnector connector : entityConnectors )
    {
      Class anotherEntity = connector.getPropertyOfAnotherEntity( entity ).getDeclaringClass();
      
      //if the connector's another entity already inside the network, this entity can be added to the network.
      if( networkEntities.contains( anotherEntity ) )
      {
        isEntityDirectlyConnectToNetwork = true;
        break;
      }
    }
    
    boolean isRelationMutual = connectorsResolver.isRelationMutual();
    if( isRelationMutual )
    {
      // the relationship is mutual, which mean no entity of network directly connected to this entity neither.
      if( !isEntityDirectlyConnectToNetwork )
        return false;
      network.put( entity, entityConnectors );
      return true;
    }


    // relationship is not mutual, also have to check if there are any entities inside network connected to this entity
    // the connectors of the entities inside of network can get from this network
    // as this network in fact is WholeEntityNetwork ( namely, all the connectors of the entities of the network have been added ) 
    connectors = new HashSet< EntityConnector >();

    for( Class networkEntity : networkEntities )
    {
      Set< EntityConnector > networkEntityConnectors = getAllConnectors();
      if( networkEntityConnectors == null || networkEntityConnectors.isEmpty() )
        continue;
      
      for( EntityConnector networkEntityConnector : networkEntityConnectors )
      {
        Class[] connectorEntities = networkEntityConnector.getEntities();
        if( entity.equals( connectorEntities[0] ) || entity.equals( connectorEntities[1] ) )
        {
          //this is the correct connector
          connectors.add( networkEntityConnector );
        }
      }
    }
    
    if( !connectors.isEmpty() )
    {
      network.put( entity, connectors );
      return true;
    }
    
    return false;
  }
}
