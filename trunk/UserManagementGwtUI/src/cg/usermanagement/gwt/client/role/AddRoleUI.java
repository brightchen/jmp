package cg.usermanagement.gwt.client.role;

import cg.gwt.components.client.ui.UIPanelComposite;
import cg.gwt.components.client.ui.components.ButtonUI;
import cg.usermanagement.gwt.shared.data.AddRoleData;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class AddRoleUI extends UIPanelComposite< AddRoleData, HorizontalPanel >
{
  private TextBox roleNameField;

  public AddRoleUI( AddRoleData data )
  {
    setData( data );
   
    Label roleNameLabel = new Label( data.getRoleNameTitle() );
    addChild( roleNameLabel );
    
    roleNameField = new TextBox();
    roleNameField.setText( data.getName() );
    addChild( roleNameField );
    
    //button
    AddRoleEvent saveRoleEvent = new AddRoleEvent()
    {
      @Override
      public AddRoleData getData()
      {
        AddRoleData roleData = AddRoleUI.this.getData();
        roleData.setName( roleNameField.getText() );
        return roleData;
      }
    };

    ButtonUI< AddRoleData > saveButton = new ButtonUI< AddRoleData >( data.getSaveButtonData() );
    saveButton.addClickEvent( saveRoleEvent );
    
    addChild( saveButton );
  }
  
  @Override
  protected HorizontalPanel buildContainer()
  {
    return new HorizontalPanel();
  }

}
