package cg.usermanagement.gwt.shared.data;

import cg.gwt.components.shared.data.PartData;

public class SystemUserLoginData implements PartData
{
  private static final long serialVersionUID = 665267442451753317L;
  
  private String userName;
  private String password;
  
  public String getUserName()
  {
    return userName;
  }
  public void setUserName( String userName )
  {
    this.userName = userName;
  }
  public String getPassword()
  {
    return password;
  }
  public void setPassword( String password )
  {
    this.password = password;
  }
}