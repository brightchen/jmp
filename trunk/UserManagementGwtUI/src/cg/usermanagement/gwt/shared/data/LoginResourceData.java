package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

public class LoginResourceData extends UserManagementResourceData implements Serializable
{
  private static final long serialVersionUID = 4036326632774416259L;

  private String title;   //the title of the login section
  private String name;
  private String password;
  private String login;   //the text in the login button
  
  public String getName()
  {
    return name;
  }
  public void setName( String name )
  {
    this.name = name;
  }
  public String getPassword()
  {
    return password;
  }
  public void setPassword( String password )
  {
    this.password = password;
  }
  public String getTitle()
  {
    return title;
  }
  public void setTitle( String title )
  {
    this.title = title;
  }
  public String getLogin()
  {
    return login;
  }
  public void setLogin( String login )
  {
    this.login = login;
  }
  
}
