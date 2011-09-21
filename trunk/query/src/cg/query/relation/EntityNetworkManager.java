package cg.query.relation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cg.common.util.ObjectUtil;
import cg.common.util.StringUtil;


/**
 * This class manages EntityNetworks, such creation/search etc
 * @author bchen
 *
 */
@SuppressWarnings( "rawtypes" )
public class EntityNetworkManager
{
  private static EntityNetworkManager defaultInstance;
  private Map< Class, WholeEntityNetwork > wholeEntityNetworkMap = new HashMap< Class, WholeEntityNetwork >();
  private Set< EntityNetwork > entityNetworks = new HashSet< EntityNetwork >();
  
  public static EntityNetworkManager defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( EntityNetworkManager.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new EntityNetworkManager();
        }
      }
    }
    return defaultInstance;
  }

  private EntityNetworkManager(){}
  
  /**
   * the WholeEntityNetworks should not have any intersection.
   * So we can lookup the WholeEntityNetwork by any entity which supposes inside of it. 
   * @param Entity: the entity which inside the WholeEntityNetwork which we are lookup
   * @return: the WholeEntityNetwork which includes the entity, returns null if no WholeEntityNetwork found
   */
  public WholeEntityNetwork lookupWholeEntityNetwork( Class entity )
  {
    return wholeEntityNetworkMap.get( entity );
  }
  
  public EntityNetwork getEntityNetworkByName( String name )
  {
    if( StringUtil.isNullOrEmpty( name ) )
      return null;
    for( EntityNetwork entityNetwork : entityNetworks )
    {
      if( name.equals( entityNetwork.getName() ) )
        return entityNetwork;
    }
    return null;
  }

  /**
   * add an EntityNetwork into the EntityNetworkManager
   * @param entityNetwork
   */
  public void addEntityNetwork( EntityNetwork entityNetwork )
  {
    entityNetworks.add( entityNetwork );
  }
  
  /**
   * add a WholeEntityNetwork into the EntityNetworkManager
   * the wholeEntityNetwork maybe already added
   * we should check if the adding WholeEntityNetwork has intersection with the others
   * @param wholeEntityNetwork: the entity network which going to be added into the manager
   */
  public void addWholeEntityNetwork( WholeEntityNetwork wholeEntityNetwork )
  {    
    Set< Class > networkEntities = wholeEntityNetwork.getEntities();
    Set< Class > wholeNetworkEntities = wholeEntityNetworkMap.keySet();
    if( wholeNetworkEntities.retainAll( networkEntities ) )
    {
      // the wholeNetworkEntities changed, so they have intersection
      if( ObjectUtil.equals( wholeEntityNetwork, networkEntities ) )
      {
        // the adding network already added to the manager
        return;
      }
      // the adding network has intersection with the manager
      throw new EntityNetworkResolvingException( "the adding network has intersection with the manager." );
    }
    for( Class entity : networkEntities )
    {
      wholeEntityNetworkMap.put( entity, wholeEntityNetwork );
    }
    addEntityNetwork( wholeEntityNetwork );
  }
  
  /**
   * 
   * @param aliasEntityMap
   * @return
   */
  public EntityNetwork resolveNetwork( Map< String, Class<?> > aliasEntityMap )
  {
    
  }
}
