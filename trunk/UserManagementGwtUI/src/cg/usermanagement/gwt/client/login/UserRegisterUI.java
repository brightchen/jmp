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
  private TextBox userNameField;
  private TextBox passwordField;
  private TextBox firstNameField;
  private TextBox middleNameField;
  private TextBox lastNameField;
  
  private AlignedUIGroup< String, FlexTable > userInfoUI;
  
  public UserRegisterUI( UserRegisterData data )
  {
    setData( data );
    userNameField = new TextBox();
    passwordField = new PasswordTextBox();
    firstNameField = new TextBox();
    middleNameField = new TextBox();
    lastNameField = new TextBox();
    
    List< String > userInfo = new ArrayList< String >();
    userInfo.add( "user name:" );
    userInfo.add( "" );
    userInfo.add( "password:" );
    userInfo.add( "" );
    userInfo.add( "first name:" );
    userInfo.add( "" );
    userInfo.add( "middle name:" );
    userInfo.add( "" );
    userInfo.add( "last name" );
    userInfo.add( "" );
    
    userInfoUI = new AlignedUIGroup< String, FlexTable >( userInfo, new FlexTable() )
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
          w = userNameField;
        }
        else if( index == 3 )
        {
          w = passwordField;
        }
        else if( index == 5 )
        {
          w = firstNameField;
        }
        else if( index == 7 )
        {
          w = middleNameField;
        }
        else if( index == 9 )
        {
          w = lastNameField;
        }

        return w;
      }
    };
    
    addChild( userInfoUI );

    
    UserRegisterEvent registerEvent = new UserRegisterEvent()
    {
      @Override
      public UserRegisterData getData()
      {
        // get data from component when event trigger
        UserRegisterData registerData = new UserRegisterData();
        registerData.setUserName( userNameField.getText() );
        registerData.setPassword( passwordField.getText() );
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
