package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RoleDetailData implements Serializable
{
  private static final long serialVersionUID = 2876214223357809584L;
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
