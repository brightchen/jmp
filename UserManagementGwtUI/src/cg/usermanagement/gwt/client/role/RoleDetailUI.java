package cg.usermanagement.gwt.client.role;

import cg.gwt.components.client.ui.UIPanelComposite;
import cg.usermanagement.gwt.shared.data.RoleDetailData;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RoleDetailUI extends UIPanelComposite< RoleDetailData, VerticalPanel >
{
  private TextBox roleNameField;

  public RoleDetailUI( RoleDetailData data )
  {
    setData( data );

  }
  
  @Override
  protected VerticalPanel buildContainer()
  {
    return new VerticalPanel();
  }

}
