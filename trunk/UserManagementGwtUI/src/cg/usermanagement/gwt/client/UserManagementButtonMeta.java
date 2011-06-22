package cg.usermanagement.gwt.client;

import cg.gwt.components.shared.data.ButtonData;

public enum UserManagementButtonMeta
{
  SEARCH_USER( "Search User" ),
  
  SEARCH_ACCOUNT( "Search Account" ),
  ADD_ACCOUNT( "Add Account", "Add an account for this user" ),
  
  SEARCH_ROLE( "Search Role" ),
  ADD_ROLE( "Add Role", "add a role to the system" ),
  
  ADD_PERMISSION( "Add Permission", "add a permission to the system" );
  
  private ButtonData buttonData;
  private UserManagementButtonMeta( String text )
  {
    this( text, text );
  }
  
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
