package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.IButtonDataSupport;
import cg.gwt.components.shared.data.ResourceData;

public class AddRoleResourceData extends ResourceData implements IButtonDataSupport, Serializable
{
  private static final long serialVersionUID = -8086359626588958685L;

  private String roleName;
  private String saveRoleText;
  private String saveRoleTitle;
  
  public String getRoleName()
  {
    return roleName;
  }
  public void setRoleName( String roleName )
  {
    this.roleName = roleName;
  }
  public String getSaveRoleText()
  {
    return saveRoleText;
  }
  public void setSaveRoleText( String saveRoleText )
  {
    this.saveRoleText = saveRoleText;
  }
  public String getSaveRoleTitle()
  {
    return saveRoleTitle;
  }
  public void setSaveRoleTitle( String saveRoleTitle )
  {
    this.saveRoleTitle = saveRoleTitle;
  }
  
  @Override
  public String getButtonText()
  {
    return getSaveRoleText();
  }
  @Override
  public String getButtonTitle()
  {
    return getSaveRoleTitle();
  }

}
