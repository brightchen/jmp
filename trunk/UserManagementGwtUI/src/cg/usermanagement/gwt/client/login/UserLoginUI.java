package cg.usermanagement.gwt.client.login;

import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.client.ui.AlignedUIGroup;
import cg.gwt.components.client.ui.UIPanelComposite;
import cg.gwt.components.client.ui.event.GwtEventDelegateHandler;
import cg.usermanagement.gwt.shared.data.UserLoginData;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserLoginUI extends UIPanelComposite< UserLoginData, VerticalPanel >
{
  private TextBox userNameField;
  private TextBox passwordField;
  private AlignedUIGroup< String, FlexTable > userPassUI;
  
  public UserLoginUI( UserLoginData data )
  {
    setData( data );
    
    List< String > userPassData = new ArrayList< String >();
    userPassData.add( "system user name/id:" );
    userPassData.add( "" );
    userPassData.add( "password:" );
    userPassData.add( "" );
    
    userNameField = new TextBox();
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
      public UserLoginData getData()
      {
        // TODO Auto-generated method stub
        return new UserLoginData( userNameField.getText(), passwordField.getText() );
      }
    };
    
    final Button loginButton = new Button( "Login" );
    
    loginButton.addClickHandler( new GwtEventDelegateHandler< UserLoginData, LoginEvent >( loginEvent ) );
    addChild( loginButton );
  }
  
  @Override
  protected VerticalPanel buildContainer()
  {
    return new VerticalPanel();
  }
  
}
