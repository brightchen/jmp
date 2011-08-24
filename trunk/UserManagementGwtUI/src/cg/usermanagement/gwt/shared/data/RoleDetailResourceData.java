package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.contentdata.shared.ResourceData;

public class RoleDetailResourceData extends ResourceData implements Serializable
{
  private static final long serialVersionUID = -3519251032463810421L;

  // the text for role-detail section
  private String roleDetailSectionText;
  private String roleId;
  private String roleName;
  
  // the text for assign-permissions section
  private String assignPermissionSectionText;
  
  public String getRoleDetailSectionText()
  {
    return roleDetailSectionText;
  }
  public void setRoleDetailSectionText( String roleDetailSectionText )
  {
    this.roleDetailSectionText = roleDetailSectionText;
  }
  public String getAssignPermissionSectionText()
  {
    return assignPermissionSectionText;
  }
  public void setAssignPermissionSectionText( String assignPermissionSectionText )
  {
    this.assignPermissionSectionText = assignPermissionSectionText;
  }
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
