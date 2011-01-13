package cg.usermanagement.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Login implements EntryPoint
{
  public void onModuleLoad()
  {
    FlexTable t = new FlexTable();

    t.setText(0, 0, "user name");
    final TextBox nameField = new TextBox();
    nameField.setText( "input user name here" );
    nameField.setFocus( true );
    nameField.selectAll();
    t.setWidget( 1, 0, nameField );
    
    t.setText(0, 1, "password");
    final TextBox passwordField = new TextBox();
    passwordField.setText( "input password here" );
    t.setWidget( 1, 1, passwordField );

    final Button loginButton = new Button("Login");
    t.setWidget(1, 2, loginButton);

//    RootPanel.get( "content" ).add( content );
    RootPanel rp = RootPanel.get();
    rp.add( t );



    // Add a handler to send the name to the server
    LoginHandler handler = new LoginHandler( nameField, passwordField )
    {
      @Override
      public void onClick( ClickEvent event )
      {
        super.onClick( event );
        loginButton.setEnabled( false );
        loginButton.setFocus( false );
      }
    };

    //    sendButton.addClickHandler( handler );
    // Add a handler to close the DialogBox
    loginButton.addClickHandler( handler );
    loginButton.addKeyUpHandler( handler );
  }    

  // Create a handler for the sendButton and nameField
  static class LoginHandler implements ClickHandler, KeyUpHandler
  {
    private TextBox tbUserName;
    private TextBox tbPassword;
    private IAuthenticateServiceAsync userService = GWT.create( IAuthenticateService.class );
    
    public LoginHandler( TextBox tbUserName, TextBox tbPassword )
    {
      this.tbUserName = tbUserName;
      this.tbPassword = tbPassword;
    }
    /**
     * Fired when the user clicks on the sendButton.
     */
    public void onClick( ClickEvent event )
    {
      onLogin();
    }

    /**
     * Fired when the user types in the nameField.
     */
    public void onKeyUp( KeyUpEvent event )
    {
      if ( event.getNativeKeyCode() == KeyCodes.KEY_ENTER )
      {
        onLogin();
      }
    }

    /**
     * Send the name from the nameField to the server and wait for a response.
     */
    private void onLogin()
    {
      String userName = tbUserName.getText();
      String password = tbPassword.getText();
      userService.login( userName, password, new AsyncCallback< Void >()
      {
        @Override
        public void onFailure( Throwable exception )
        {
          displayMessage( "login failed due to " + exception.toString() );
        }
        
        @Override
        public void onSuccess( Void returned )
        {
          displayMessage( "login successful" );
        }
      } );
    }
    
    protected void displayMessage( String message )
    {
      DialogBox  dlg = new DialogBox();
      dlg.setTitle( "Message" );
      dlg.setText( message );
      dlg.show();
    }
  }

}
