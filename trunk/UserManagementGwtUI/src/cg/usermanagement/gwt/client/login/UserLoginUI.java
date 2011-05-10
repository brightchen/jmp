package cg.usermanagement.gwt.client.login;

import cg.gwt.components.client.ui.UIComposite;
import cg.gwt.components.client.ui.decorator.RubyTextLeftDecorator;
import cg.usermanagement.gwt.shared.data.SystemUserLoginData;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UserLoginUI extends UIComposite< SystemUserLoginData, VerticalPanel >
{
  private TextBox accountField;
  private TextBox passwordField;
  private VerticalPanel realComponent;
  
  public UserLoginUI( SystemUserLoginData data )
  {
    setData( data );
    
    accountField = new TextBox();
    addChild( new RubyTextLeftDecorator<TextBox>( "system account name/id:", accountField ) );
    
    passwordField = new PasswordTextBox();
    addChild( new RubyTextLeftDecorator<TextBox>( "password", passwordField ) );
    
    final Button loginButton = new Button( "Login" );
    addChild( loginButton );
  }
  
  @Override
  public VerticalPanel getRealComponent()
  {
    return realComponent;
  }
  
  @Override
  protected VerticalPanel buildContainer()
  {
    return new VerticalPanel();
  }
  
}
