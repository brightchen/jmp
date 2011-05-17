package cg.usermanagement.gwt.client.login;

import cg.gwt.components.client.ui.old.IUIObjectDigester;
import cg.gwt.components.client.ui.old.UIObjectBuilder;
import cg.usermanagement.gwt.client.ui.UserManagementClientPanelBuilder;
import cg.usermanagement.gwt.client.ui.UserManagementMenuBarBuilder;
import cg.usermanagement.gwt.client.ui.UserManagementPanelBuilder;
import cg.usermanagement.gwt.shared.data.UserLoginData;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SystemUserLoginTransformer extends UIObjectBuilder< UserLoginData, FlexTable > implements IUIObjectDigester
{
  private TextBox nameField;
  private TextBox passwordField;
  
  //create an empty data instance
  @Override
  protected UserLoginData createData()
  {
    return new UserLoginData();
  }

  @Override
  public FlexTable build()
  {
    UserLoginData data = getData();
    
    FlexTable table = new FlexTable();

    table.setText( 0, 0, "system account name/id:" );
    nameField = new TextBox();
    nameField.setText( data == null ? "" : data.getAccountId() );
    nameField.setFocus( true );
    nameField.selectAll();
    table.setWidget( 1, 0, nameField );

    table.setText( 0, 1, "password" );
    passwordField = new PasswordTextBox();
    passwordField.setText( "" );
    table.setWidget( 1, 1, passwordField );

    // login button
//    {
//      final Button loginButton = new Button( "Login" );
//      // Add a handler to send the name to the server
//      LoginEvent handler =  new LoginEvent()
//                              {
//                                @Override
//                                protected void updateData()
//                                {
//                                  SystemUserLoginTransformer.this.digest();
//                                  SystemUserLoginData data = getData();
//                                  setAccountId( data.getAccountId() );
//                                  setPassword( data.getPassword() );
//                                }
//
//                                @Override
//                                protected void onLoginSuccess()
//                                {
//                                  SystemUserLoginTransformer.this.onLoginSuccess();
//                                }
//                              };
//      
//      loginButton.addClickHandler( handler );
//      loginButton.addKeyUpHandler( handler );
//
//      table.setWidget( 1, 2, loginButton );
//    }
    return table;
    
  }
  
  //get the data from UI
  @Override
  public void digest()
  {
    UserLoginData data = getData();
    if( data == null )
    {
      data = new UserLoginData();
      setData( data );
    }
    data.setAccountId( nameField.getText() );
    data.setPassword( passwordField.getText() );
  }

  public void onLoginSuccess()
  {
    RootPanel rp = RootPanel.get();
    rp.remove( 0 );
    
    UserManagementMenuBarBuilder menuBuilder = new UserManagementMenuBarBuilder();
    UserManagementClientPanelBuilder clientBuilder = new UserManagementClientPanelBuilder();
    UserManagementPanelBuilder panelBuilder = new UserManagementPanelBuilder( menuBuilder, clientBuilder );
    panelBuilder.setContainer( new VerticalPanel() );
    rp.add( panelBuilder.build() );
  }
}