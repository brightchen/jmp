package cg.query.relation;

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
   * add entity and its connector into the network
   * the WholeEntityNetwork will add as more entity/connector as possible 
   * 
   * @precondition the caller should make sure the connectorsOfEntity are the connectors of this entity
   * @param entity: the entity going to add to the network
   * @param connectorsOfEntity: the connectors of this entity
   * @return whether add entity to network successful.
   */
  @Override
  protected boolean addDirectlyConnectedEntity( Class entity, Set< EntityConnector > connectorsOfEntity )
  {
    Set< Class > entities = network.keySet();
    //add all the connectors of this entity
    network.put( entity, connectorsOfEntity );

    return true;
  }
}
