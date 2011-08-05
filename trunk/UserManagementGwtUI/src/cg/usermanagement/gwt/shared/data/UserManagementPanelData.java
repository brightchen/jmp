package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.shared.data.UIContentData;

public class UserManagementPanelData extends UIContentData<UserManagementPanelResourceData> implements Serializable
{
  private static final long serialVersionUID = -2787639626749698020L;

  private List< UserManagementPanelButtonData > buttonDatas = new ArrayList< UserManagementPanelButtonData >();

  public UserManagementPanelData()
  {
    addButtonData( new UserManagementPanelButtonData( this ) );
  }
  
  public List< UserManagementPanelButtonData > getButtonDatas()
  {
    return buttonDatas;
  }

  public void setButtonDatas( List< UserManagementPanelButtonData > buttonDatas )
  {
    this.buttonDatas = buttonDatas;
  }
  
  public void addButtonData( UserManagementPanelButtonData buttonData )
  {
    buttonDatas.add( buttonData );
  }
  
}
