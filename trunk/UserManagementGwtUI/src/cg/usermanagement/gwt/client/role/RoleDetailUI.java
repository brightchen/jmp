package cg.usermanagement.gwt.client.role;

import cg.gwt.components.client.ui.UIPanelComposite;
import cg.gwt.components.client.ui.components.ButtonUI;
import cg.usermanagement.gwt.shared.data.RoleData;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RoleDetailUI extends UIPanelComposite< RoleData, VerticalPanel >
{
  private TextBox roleNameField;

  public RoleDetailUI( RoleData data )
  {
    setData( data );
   
    Label roleNameLabel = new Label( data.getRoleNameTitle() );
    addChild( roleNameLabel );
    
    roleNameField = new TextBox();
    roleNameField.setText( data.getName() );
    addChild( roleNameField );
    
    //button
    ButtonUI saveButton = new ButtonUI( data.getSaveButtonData() );
    addChild( saveButton );
  }
}
