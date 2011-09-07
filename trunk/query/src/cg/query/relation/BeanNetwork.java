package cg.query.relation;

import java.util.ArrayList;
import java.util.List;

/*
 * BeanNetwork keeps all the relationship among beans
 */
public class BeanNetwork
{
  private static BeanNetwork instance;
  public static BeanNetwork instance()
  {
    if( instance == null )
    {
      synchronized( BeanNetwork.class )
      {
        if( instance == null )
        {
          instance = new BeanNetwork();
        }
      }
    }
    return instance;
  }
  
  private List< BeanConnector > connectors = new ArrayList< BeanConnector >();
  
  public void addConnector( BeanConnector connector )
  {
    connectors.add( connector );
  }
}
