package cg.usermanagement.gwt.client.login;

import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.client.ui.AlignedUIGroup;
import cg.gwt.components.client.ui.UIPanelComposite;
import cg.gwt.components.client.ui.event.GwtEventDelegateHandler;
import cg.usermanagement.gwt.shared.data.UserRegisterData;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserRegisterUI extends UIPanelComposite< UserRegisterData, VerticalPanel >
{
  private TextBox accountField;
  private TextBox passwordField;
  private TextBox userNameField;
  private TextBox firstNameField;
  private TextBox middleNameField;
  private TextBox lastNameField;
  
  private AlignedUIGroup< String, FlexTable > accountInfoUI;
  
  public UserRegisterUI( UserRegisterData data )
  {
    setData( data );

    accountField = new TextBox();
    passwordField = new PasswordTextBox();
    userNameField = new TextBox();
    firstNameField = new TextBox();
    middleNameField = new TextBox();
    lastNameField = new TextBox();
    
    List< String > accountInfo = new ArrayList< String >();
    accountInfo.add( "account name/id:" );
    accountInfo.add( "" );
    accountInfo.add( "password:" );
    accountInfo.add( "" );
    accountInfo.add( "user name:" );
    accountInfo.add( "" );
    accountInfo.add( "first name:" );
    accountInfo.add( "" );
    accountInfo.add( "middle name:" );
    accountInfo.add( "" );
    accountInfo.add( "last name" );
    accountInfo.add( "" );
    
    accountInfoUI = new AlignedUIGroup< String, FlexTable >( accountInfo, new FlexTable() )
    {
      @Override
      public Widget buildChildComponent( String childData, int index )
      {
        Widget w = null;
        if( index % 2 == 0 )
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
        else if( index == 5 )
        {
          w = userNameField;
        }
        else if( index == 7 )
        {
          w = firstNameField;
        }
        else if( index == 9 )
        {
          w = middleNameField;
        }
        else if( index == 11 )
        {
          w = lastNameField;
        }

        return w;
      }
    };
    
    addChild( accountInfoUI );

    
    UserRegisterEvent registerEvent = new UserRegisterEvent()
    {
      @Override
      public UserRegisterData getData()
      {
        // get data from component when event trigger
        UserRegisterData registerData = new UserRegisterData();
        registerData.setAccountId( accountField.getText() );
        registerData.setPassword( passwordField.getText() );
        registerData.setUserName( userNameField.getText() );
        registerData.setFirstName( firstNameField.getText() );
        registerData.setMiddleName( middleNameField.getText() );
        registerData.setLastName( lastNameField.getText() );
        
        return registerData;
      }
    };
    
    final Button registerButton = new Button( "Register Account" );
    registerButton.addClickHandler( new GwtEventDelegateHandler< UserRegisterData, UserRegisterEvent >( registerEvent ) );
    addChild( registerButton );

  }
  
  
  @Override
  protected VerticalPanel buildContainer()
  {
    return new VerticalPanel();
  }
}
