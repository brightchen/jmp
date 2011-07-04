package cg.usermanagement.gwt.client.login;

import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.client.ui.AlignedUIGroup;
import cg.gwt.components.client.ui.UIPanelComposite;
import cg.gwt.components.client.ui.event.GwtEventDelegateHandler;
import cg.usermanagement.gwt.shared.data.LoginData;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginUI extends UIPanelComposite< LoginData, VerticalPanel >
{
  private TextBox userNameField;
  private TextBox passwordField;
  private AlignedUIGroup< String > userPassUI;
  
  public LoginUI( LoginData data )
  {
    setData( data );
    
    List< String > userPassData = new ArrayList< String >();
    userPassData.add( data.getResourceData().getName() );
    userPassData.add( data.getName() );
    userPassData.add( data.getResourceData().getPassword() );
    userPassData.add( data.getPassword() );
    
    userNameField = new TextBox();
    userNameField.setText( data.getName() );
    passwordField = new PasswordTextBox();
    passwordField.setText( data.getPassword() );
    
    userPassUI = new AlignedUIGroup< String >( userPassData )
    {
      @Override
      public Widget buildChildComponent( String childData, int index )
      {
        Widget w = null;
        if( index == 0 || index == 2 )
        {
          w = new Label( childData );
        }
        else if( index == 1 )
        {
          w = userNameField;
        }
        else if( index == 3 )
        {
          w = passwordField;
        }
        return w;
      }
    };
    
    addChild( userPassUI );

    
    LoginEvent loginEvent = new LoginEvent()
    {
      @Override
      public LoginData getData()
      {
        LoginData loginData = LoginUI.this.getData();
        loginData.setName( userNameField.getText() );
        loginData.setPassword( passwordField.getText() );
        return loginData;
      }
    };
    
    final Button loginButton = new Button( "Login" );
    
    loginButton.addClickHandler( new GwtEventDelegateHandler< LoginData, LoginEvent >( loginEvent ) );
    addChild( loginButton );
  }
  
  @Override
  protected VerticalPanel buildContainer()
  {
    return new VerticalPanel();
  }
  
}
