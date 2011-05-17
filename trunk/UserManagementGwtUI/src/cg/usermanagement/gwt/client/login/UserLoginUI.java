package cg.usermanagement.gwt.client.login;

import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.client.ui.AlignedUIGroup;
import cg.gwt.components.client.ui.UIComposite;
import cg.gwt.components.client.ui.event.GwtEventDelegateHandler;
import cg.usermanagement.gwt.client.login.LoginEvent.LoginEventData;
import cg.usermanagement.gwt.shared.data.UserLoginData;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserLoginUI extends UIComposite< UserLoginData, VerticalPanel >
{
  private TextBox accountField;
  private TextBox passwordField;
  private VerticalPanel realComponent;
  private AlignedUIGroup< String, FlexTable > userPassUI;
  
  public UserLoginUI( UserLoginData data )
  {
    setData( data );
    
    List< String > userPassData = new ArrayList< String >();
    userPassData.add( "system account name/id:" );
    userPassData.add( "" );
    userPassData.add( "password:" );
    userPassData.add( "" );
    
    accountField = new TextBox();
    passwordField = new PasswordTextBox();
    
    userPassUI = new AlignedUIGroup< String, FlexTable >( userPassData, new FlexTable() )
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
          w = accountField;
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
      public LoginEventData getData()
      {
        // TODO Auto-generated method stub
        return new LoginEventData( accountField.getText(), passwordField.getText() );
      }
    };
    
    final Button loginButton = new Button( "Login" );
    
    loginButton.addClickHandler( new GwtEventDelegateHandler< LoginEventData, LoginEvent >( loginEvent ) );
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
