package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.shared.data.ButtonData;

public class UserManagementPanelData implements Serializable
{
  private static final long serialVersionUID = -2787639626749698020L;

  private List< ButtonData > buttonDatas = new ArrayList< ButtonData >();

  public List< ButtonData > getButtonDatas()
  {
    return buttonDatas;
  }

  public void setButtonDatas( List< ButtonData > buttonDatas )
  {
    this.buttonDatas = buttonDatas;
  }
  
  public void addButtonData( ButtonData buttonData )
  {
    buttonDatas.add( buttonData );
  }
  
}
