package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

public class UserManagementStartResourceData extends UserManagementResourceData implements Serializable
{
  private static final long serialVersionUID = -2303489070874546282L;
  
  private String loginTitle;    //the title of login section
  private String registerTitle;
  
  public String getLoginTitle()
  {
    return loginTitle;
  }
  public void setLoginTitle( String loginTitle )
  {
    this.loginTitle = loginTitle;
  }
  public String getRegisterTitle()
  {
    return registerTitle;
  }
  public void setRegisterTitle( String registerTitle )
  {
    this.registerTitle = registerTitle;
  } 
}
