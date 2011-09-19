package cg.query.relation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class manages EntityNetworks, such creation/search etc
 * @author bchen
 *
 */
public class EntityNetworkManager
{
  private static EntityNetworkManager defaultInstance;
  private Map< Class, WholeEntityNetwork > wholeEntityNetworkMap = new HashMap< Class, WholeEntityNetwork >();
  private List< EntityNetwork > entityNetworks;
  
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

}
