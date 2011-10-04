package cg.tester;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cg.query.relation.EntityNetwork;
import cg.query.relation.EntityNetworkManager;
import cg.usermanagement.model.Account;
import cg.usermanagement.model.Role;
import cg.usermanagement.model.User;

@SuppressWarnings( "rawtypes" )
public class EntityNetworkTester
{
  public static List< Set< Class > > getEntitySets() 
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
    List< Set< Class > > entitySets  = getEntitySets();
    for( Set< Class > entitySet : entitySets )
    {
      EntityNetwork entityNetwork = EntityNetworkManager.defaultInstance().buildWholeEntityNetwork( entitySet );
      entityNetwork.getEntities();
    }
  }
}
