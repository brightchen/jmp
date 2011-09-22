package cg.query.relation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings( "rawtypes" )
public class BuildableEntityNetwork extends EntityNetwork
{
  private IEntityConnectorsResolver entityConnectorsResolver;

  
//public WholeEntityNetwork buildWholeEntityNetwork( Class entity )
//{
//  IEntityConnectorsResolver resolver = getEntityConnectorsResolver();
//  Set< EntityConnector > directConnectors = resolver.getDirectConnectors( entity );
//  WholeEntityNetwork network = new WholeEntityNetwork();
//  network.addDirectlyConnectedEntity( entity );
//}

  /**
   * build a network which covers all the entities.
   * the built network will be added into this manager
   * @param entities
   * @return: the built network 
   */
  public boolean buildEntityNetwork( Set< Class > entities )
  {
    int totalResolvedEntities = 0;
    List< Class > notAddedEntities = new ArrayList< Class >();
    boolean hasEntityResolved = false;
    do
    {
      notAddedEntities.clear();
      hasEntityResolved = false;
      
      for( Class entity : entities )
      {
        boolean addSuccessful = addDirectlyConnectedEntity( entity );
        if( !addSuccessful )
          notAddedEntities.add( entity );
        else
        {
          hasEntityResolved = true;
          ++totalResolvedEntities;
        }
      }
    }while( notAddedEntities.size() > 0 && hasEntityResolved );
    
    return ( totalResolvedEntities == entities.size() );
  }
  
  /**
   * get the connectors which directly connected to the entity.
   * this class just simply returns null 
   * @param entity
   * @return
   */
  @Override
  protected Set< EntityConnector > getDirectConnectors( Class entity )
  {
    return getEntityConnectorsResolver().getDirectConnectors( entity );
  }

  public IEntityConnectorsResolver getDefaultEntityConnectorsResolver()
  {
    return EntityConnectorsAnnotationResolver.defaultInstance();
  }
  
  public IEntityConnectorsResolver getEntityConnectorsResolver()
  {
    return entityConnectorsResolver == null ? getDefaultEntityConnectorsResolver() : entityConnectorsResolver;
  }

  public void setEntityConnectorsResolver( IEntityConnectorsResolver entityConnectorsResolver )
  {
    this.entityConnectorsResolver = entityConnectorsResolver;
  }
  
  
}
