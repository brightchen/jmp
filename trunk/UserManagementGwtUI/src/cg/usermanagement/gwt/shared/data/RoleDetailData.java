package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RoleDetailData implements Serializable
{
  private static final long serialVersionUID = 2876214223357809584L;
 
  private String roleIdTitle = "Role Id: ";
  private Long id;  //role id
  private String roleNameTitle = "Role Name: ";
  private String name;    //role name

  
  public String getRoleIdTitle()
  {
    return roleIdTitle;
  }
  public void setRoleIdTitle( String roleIdTitle )
  {
    this.roleIdTitle = roleIdTitle;
  }
  public Long getId()
  {
    return id;
  }
  public void setId( Long id )
  {
    this.id = id;
  }
  public String getRoleNameTitle()
  {
    return roleNameTitle;
  }
  public void setRoleNameTitle( String roleNameTitle )
  {
    this.roleNameTitle = roleNameTitle;
  }
  public String getName()
  {
    return name;
  }
  public void setName( String name )
  {
    this.name = name;
  }
  private Set< PermissionData > permissionDatas = new HashSet< PermissionData >();

  public Set< PermissionData > getPermissionDatas()
  {
    return permissionDatas;
  }
  public void setPermissionDatas( Set< PermissionData > permissionDatas )
  {
    this.permissionDatas = permissionDatas;
  }
  public void addPermissionData( PermissionData permissionData )
  {
    permissionDatas.add( permissionData );
  }
}
