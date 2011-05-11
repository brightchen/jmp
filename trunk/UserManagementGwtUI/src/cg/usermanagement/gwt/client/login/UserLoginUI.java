package cg.usermanagement.gwt.client.login;

import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.client.ui.AlignedUIGroup;
import cg.gwt.components.client.ui.UIComposite;
import cg.usermanagement.gwt.shared.data.SystemUserLoginData;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserLoginUI extends UIComposite< SystemUserLoginData, VerticalPanel >
{
  private TextBox accountField;
  private TextBox passwordField;
  private VerticalPanel realComponent;
  private AlignedUIGroup< String, FlexTable > userPassUI;
  
  public UserLoginUI( SystemUserLoginData data )
  {
    setData( data );
    
    
    List< String > userPassData = new ArrayList< String >();
    userPassData.add( "system account name/id:" );
    userPassData.add( "name" );
    userPassData.add( "password:" );
    userPassData.add( "password:" );
    
    accountField = new TextBox();
    passwordField = new PasswordTextBox();
    
    userPassUI = new AlignedUIGroup< String, FlexTable >( userPassData, new FlexTable() )
    {
      @Override
      public void buildAndAddChild( String childData, int index )
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
        addChild( w );
      }
    };
    
    
    addChild( userPassUI );
    
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
