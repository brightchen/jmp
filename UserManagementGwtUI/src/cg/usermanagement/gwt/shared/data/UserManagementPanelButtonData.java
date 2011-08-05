package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.ButtonData;

/*
 * the text and title should get from the resource data
 * and it should be get after resource data injected
 */
public class UserManagementPanelButtonData extends ButtonData implements Serializable
{
  private static final long serialVersionUID = -2320463587622290724L;
  
  private UserManagementPanelData panelData;
  
  public UserManagementPanelButtonData(){}
  
  public UserManagementPanelButtonData( UserManagementPanelData panelData )
  {
    setPanelData( panelData );
  }
  
  public UserManagementPanelData getPanelData()
  {
    return panelData;
  }

  public void setPanelData( UserManagementPanelData panelData )
  {
    this.panelData = panelData;
  }

  @Override
  public String getText()
  {
    return panelData.getResourceData().getAddAccount();
  }
  
  @Override
  public String getTitle()
  {
    return panelData.getResourceData().getAddAccountTitle();
  }

}
