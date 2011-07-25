package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.shared.data.ICompositeContentData;
import cg.gwt.components.shared.data.MenuBarData;
import cg.gwt.components.shared.data.UIContentData;

public class ControlSectionData extends UIContentData< ControlSectionResourceData > implements ICompositeContentData, Serializable
{
  private static final long serialVersionUID = -5140670063119550849L;

  private List< MenuBarData > menuPanelData;

  public List< MenuBarData > getMenuPanelData()
  {
    return menuPanelData;
  }

  public void setMenuPanelData( List< MenuBarData > menuPanelData )
  {
    this.menuPanelData = menuPanelData;
  }
  
  public void addMenuBarData( MenuBarData menuBarData )
  {
    if( menuPanelData == null )
      menuPanelData = new ArrayList<MenuBarData>();
    menuPanelData.add( menuBarData );
  }

  @Override
  public List< ? extends UIContentData > getSubContentDatas()
  {
    return menuPanelData;
  }
}
