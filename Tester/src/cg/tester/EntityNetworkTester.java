package cg.tester;

import java.util.HashSet;
import java.util.Set;

import cg.model.common.Permission;
import cg.query.relation.EntityNetwork;
import cg.query.relation.EntityNetworkManager;
import cg.usermanagement.model.User;

public class EntityNetworkTester
{
  public static void main( String[] argvs )
  {
    Set< Class > entities = new HashSet< Class >();
    entities.add( User.class );
    entities.add( Permission.class );
    EntityNetwork entityNetwork = EntityNetworkManager.defaultInstance().buildEntityNetwork( entities );
    entityNetwork.getEntities();
  }
}
