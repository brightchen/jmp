package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cg.gwt.components.shared.data.ButtonData;
import cg.gwt.components.shared.data.UIContentData;
import cg.resourcemanagement.annotation.IResourceKey;

public class RoleDetailData extends UIContentData< RoleDetailResourceData > implements Serializable
{
  private static final long serialVersionUID = 2876214223357809584L;
 
  private Long id;        //role id
  private String name;    //role name

  // the permission which already assigned to this role
  private Set< PermissionData > assignedPermissions = new HashSet< PermissionData >();
  
  // the permission which has assigned to this role
  private Set< PermissionData > unassignedPermissions = new HashSet< PermissionData >();
  
  //the button of save role for adding a role
  private ButtonData saveRoleButton;
  
  //the button of save assigned permission for assigning permissions
  private ButtonData savePermissionsButton;
  
  public Long getId()
  {
    return id;
  }
  public void setId( Long id )
  {
    this.id = id;
  }
  public String getName()
  {
    return name;
  }
  public void setName( String name )
  {
    this.name = name;
  }
  public Set< PermissionData > getAssignedPermissions()
  {
    return assignedPermissions;
  }
  public void setAssignedPermissions( Set< PermissionData > assignedPermissions )
  {
    this.assignedPermissions = assignedPermissions;
  }
  public void addAssignedPermissisons( PermissionData assignedPermission )
  {
    if( assignedPermissions == null )
    {
      assignedPermissions = new HashSet< PermissionData >();
    }
    assignedPermissions.add( assignedPermission );
  }
  
  public Set< PermissionData > getUnassignedPermissions()
  {
    return unassignedPermissions;
  }
  public void setUnassignedPermissions( Set< PermissionData > unassignedPermissions )
  {
    this.unassignedPermissions = unassignedPermissions;
  }
  public void removeUnassignedPermission( PermissionData unassignedPermission )
  {
    unassignedPermissions.remove( unassignedPermission );
  }
  
  public ButtonData getSaveRoleButton()
  {
    return saveRoleButton;
  }
  @IResourceKey( moduleName = "um", className = "saverolebutton" )
  public void setSaveRoleButton( ButtonData saveRoleButton )
  {
    this.saveRoleButton = saveRoleButton;
  }
  
  public ButtonData getSavePermissionsButton()
  {
    return savePermissionsButton;
  }
  @IResourceKey( moduleName = "um", className = "savepermissionsbutton" )
  public void setSavePermissionsButton( ButtonData savePermissionsButton )
  {
    this.savePermissionsButton = savePermissionsButton;
  }

}
