package cg.tester;

import cg.usermanagement.api.IUserService;
import cg.usermanagement.api.UserSearchCriteria;
import cg.usermanagement.service.UserService;

public class QueryTester
{
  public static void main( String[] argvs )
  {
    UserSearchCriteria searchCriteria = new UserSearchCriteria();
    searchCriteria.setFirstName( "firstname" );
    searchCriteria.setLastName( "lastname" );
    IUserService userService = new UserService();
    userService.findUsers( searchCriteria );
  }
}
