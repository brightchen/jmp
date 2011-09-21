package cg.query.relation;

import java.util.Set;


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
