package cg.usermanagement.api.model;

public interface IUser extends IPerson
{
  //this is different from Entity id, for example, email address maybe the userId
  public String getUesrId();
  public void setUserId( String userId );
  
}
