package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

public class LoginResourceData implements Serializable
{
  private static final long serialVersionUID = 4036326632774416259L;

  private String name;
  private String password;
  
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
}
