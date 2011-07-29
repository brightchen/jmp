package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

public class UserManagementStartResourceData extends UserManagementResourceData implements Serializable
{
  private static final long serialVersionUID = -2303489070874546282L;
  
  private String loginTitle;    //the title of login section
  
  public String getLoginTitle()
  {
    return loginTitle;
  }
  public void setLoginTitle( String loginTitle )
  {
    this.loginTitle = loginTitle;
  }
}
