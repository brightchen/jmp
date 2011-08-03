package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cg.gwt.components.shared.data.UIContentData;

public class RoleDetailData extends UIContentData<RoleDetailResourceData> implements Serializable
{
  private static final long serialVersionUID = 2876214223357809584L;
 
  private Long id;  //role id
  private String name;    //role name

  private Set< PermissionData > permissionDatas = new HashSet< PermissionData >();
  
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
