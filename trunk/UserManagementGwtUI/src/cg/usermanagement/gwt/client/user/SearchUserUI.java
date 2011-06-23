package cg.usermanagement.gwt.client.user;

import cg.gwt.components.client.ui.UIFlexTableComposite;
import cg.gwt.components.client.ui.components.ButtonUI;
import cg.gwt.components.shared.data.ButtonData;
import cg.usermanagement.gwt.shared.data.SearchUserData;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class SearchUserUI extends UIFlexTableComposite< SearchUserData >
{
  private static final int ROW_SIZE = 2;    // how many rows in each line
  
  private TextBox nameField;
  private TextBox firstNameField;
  private TextBox lastNameField;
  private TextBox statusField;
  private TextBox phoneField;
  private TextBox emailField;
  
  public SearchUserUI( SearchUserData data )
  {
    super( data );
    
    addChild( new Label( data.getNameTitle() ) );
    nameField = new TextBox();
    nameField.setText( data.getName() );
    addChild( nameField );
    
    addChild( new Label( data.getFirstNameTitle() ) );
    firstNameField = new TextBox();
    firstNameField.setText( data.getFirstName() );
    addChild( firstNameField );

    addChild( new Label( data.getLastNameTitle() ) );
    lastNameField = new TextBox();
    lastNameField.setText( data.getLastName() );
    addChild( lastNameField );

    addChild( new Label( data.getStatusTitle() ) );
    statusField = new TextBox();
    statusField.setText( data.getStatus() );
    addChild( statusField );

    addChild( new Label( data.getPhoneTitle() ) );
    phoneField = new TextBox();
    phoneField.setText( data.getPhone() );
    addChild( phoneField );

    addChild( new Label( data.getEmailTitle() ) );
    emailField = new TextBox();
    emailField.setText( data.getEmail() );
    addChild( emailField );
    
    ButtonData buttonData = new ButtonData();
    buttonData.setText( data.getSearchButtonText() );
    buttonData.setTitle( data.getSearchButtonTitle() );
    
    ButtonUI searchButton = new ButtonUI( data.getSearchButtonData() );
    searchButton.addClickEvent( event )
  }
  
  
}
