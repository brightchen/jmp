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
    SaveRoleEvent saveRoleEvent = new SaveRoleEvent()
    {
      @Override
      public RoleData getData()
      {
        RoleData roleData = RoleDetailUI.this.getData();
        roleData.setName( roleNameField.getText() );
        return roleData;
      }
    };

    ButtonUI< RoleData > saveButton = new ButtonUI< RoleData >( data.getSaveButtonData() );
    saveButton.addClickEvent( saveRoleEvent );
    
    addChild( saveButton );
  }
}
