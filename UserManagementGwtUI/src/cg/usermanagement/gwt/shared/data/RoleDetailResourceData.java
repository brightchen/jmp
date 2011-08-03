package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.ResourceData;

public class RoleDetailResourceData extends ResourceData implements Serializable
{
  private static final long serialVersionUID = -3519251032463810421L;

  private String roleId;
  private String roleName;
  
  public String getRoleId()
  {
    return roleId;
  }
  public void setRoleId( String roleId )
  {
    this.roleId = roleId;
  }
  public String getRoleName()
  {
    return roleName;
  }
  public void setRoleName( String roleName )
  {
    this.roleName = roleName;
  }

  
}
