package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.common.property.IClassProperty;

public class LoginResourceData implements Serializable
{
  private static final long serialVersionUID = 4036326632774416259L;

  public enum LoginResourceDataProperty implements IClassProperty
  {
    name,
    password
  }
  
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
