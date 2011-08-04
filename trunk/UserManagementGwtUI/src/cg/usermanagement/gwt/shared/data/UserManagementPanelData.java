package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.shared.data.ButtonData;
import cg.gwt.components.shared.data.UIContentData;

public class UserManagementPanelData extends UIContentData<UserManagementPanelResourceData> implements Serializable
{
  private static final long serialVersionUID = -2787639626749698020L;

  private List< ButtonData > buttonDatas = new ArrayList< ButtonData >();

  public UserManagementPanelData()
  {
    addButtonData( new ButtonData()
                    {
                      private static final long serialVersionUID = -8853378531916405651L;
                      public String getText()
                      {
                        return getResourceData().getAddAccount();
                      }
                      public String getTitle()
                      {
                        return getResourceData().getAddAccountTitle();
                      }
                    } );
  }
  
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
