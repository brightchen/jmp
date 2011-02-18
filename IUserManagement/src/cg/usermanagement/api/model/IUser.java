package cg.usermanagement.api.model;

import java.util.List;

public interface IUser extends IPerson
{
  //this is different from Entity id, for example, email address maybe the userId
  //use accounts' id as id
  public List< ? extends IAccount > getAccounts();
  
  public void setAccounts( List< ? extends IAccount > accounts );
}
