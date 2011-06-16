package cg.usermanagement.gwt.client;

import cg.gwt.components.shared.data.ButtonData;

public enum UserManagementButtonMeta
{
  ADD_ROLE( "add role", "add a role to the system" ),
  ADD_PERMISSION( "add permission", "add a permission to the system" );
  
  private ButtonData buttonData;
  private UserManagementButtonMeta( String text, String title )
  {
    buttonData = new ButtonData();
    buttonData.setText( text );
    buttonData.setTitle( title );
  }
  
  public ButtonData getButtonData()
  {
    return buttonData;
  }
  
  public static UserManagementButtonMeta toMeta( ButtonData buttonData )
  {
    UserManagementButtonMeta[] metas = UserManagementButtonMeta.values();
    for( UserManagementButtonMeta meta : metas )
    {
      if( meta.getButtonData().getText().equals( buttonData.getText() ) )
        return meta;
    }
    throw new RuntimeException( "The button data is not corresponding to a UserManagementButtonMeta" );
  }
}
