package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.ResourceData;

public class RoleDetailResourceData extends ResourceData implements Serializable
{
  private static final long serialVersionUID = -3519251032463810421L;

  // the text for role-detail section
  private String roleDetailSectionText;
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
}
