package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.ButtonData;

public class AddRoleData implements Serializable
{
  private static final long serialVersionUID = -6940977542611194133L;

  private Long id;
  private String roleNameTitle = "Role Name: ";
  private String name;    //role name
  private ButtonData saveButtonData;  // add or update
  
  public AddRoleData()
  {
    saveButtonData = new ButtonData();
    saveButtonData.setText( "Save" );
    saveButtonData.setTitle( "Save the role" );
    saveButtonData.setEnabled( true );
  }
  
  public String getRoleNameTitle()
  {
    return roleNameTitle;
  }
  public void setRoleNameTitle( String roleNameTitle )
  {
    this.roleNameTitle = roleNameTitle;
  }
  public Long getId()
  {
    return id;
  }
  public void setId( Long id )
  {
    this.id = id;
  }
  public String getName()
  {
    return name;
  }
  public void setName( String name )
  {
    this.name = name;
  }

  public ButtonData getSaveButtonData()
  {
    return saveButtonData;
  }
  public void setSaveButtonData( ButtonData saveButtonData )
  {
    this.saveButtonData = saveButtonData;
  }
}