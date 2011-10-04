package cg.tester;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cg.query.relation.EntityNetworkManager;
import cg.query.relation.WholeEntityNetwork;
import cg.usermanagement.model.Account;
import cg.usermanagement.model.Role;
import cg.usermanagement.model.User;

@SuppressWarnings( "rawtypes" )
public class EntityNetworkTester
{
  private static Class[] entities = { User.class, Account.class, Role.class };
  public static  List< Set< Class > > getEntitySets() 
  {
    List< Set< Class > > entitySets = new ArrayList< Set< Class > >();

    {
      Set< Class > entities = new HashSet< Class >();
      entitySets.add( entities );
      
      entities.add( User.class );
      entities.add( Account.class );      
    }

    {
      Set< Class > entities = new HashSet< Class >();
      entitySets.add( entities );
      
      entities.add( User.class );
      entities.add( Role.class );
    }

    {
      Set< Class > entities = new HashSet< Class >();
      entitySets.add( entities );
      
      entities.add( User.class );
      entities.add( Role.class );
    }

    return entitySets;
  }
  
  public static void main( String[] argvs )
  {
    for( Class entity : entities )
    {
      WholeEntityNetwork entityNetwork = EntityNetworkManager.defaultInstance().getWholeEntityNetwork( entity );
      entityNetwork.getEntities();
    }
  }
}
