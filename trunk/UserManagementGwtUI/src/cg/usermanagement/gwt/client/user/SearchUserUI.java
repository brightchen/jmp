package cg.usermanagement.gwt.client.user;

import cg.gwt.components.client.ui.UIFlexTableComposite;
import cg.gwt.components.client.ui.components.ButtonUI;
import cg.usermanagement.gwt.shared.data.SearchUserData;
import cg.usermanagement.gwt.shared.data.SearchUserResourceData;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class SearchUserUI extends UIFlexTableComposite< SearchUserData >
{
  private TextBox nameField;
  private TextBox firstNameField;
  private TextBox lastNameField;
  private TextBox statusField;
  private TextBox phoneField;
  private TextBox emailField;
  
  public SearchUserUI( SearchUserData data )
  {
    super( data );
    SearchUserResourceData resourceData = data.getResourceData();
    
    addChild( new Label( resourceData.getUserName() ) );
    nameField = new TextBox();
    nameField.setText( data.getName() );
    addChild( nameField );
    
    addChild( new Label( resourceData.getFirstName() ) );
    firstNameField = new TextBox();
    firstNameField.setText( data.getFirstName() );
    addChild( firstNameField );

    addChild( new Label( resourceData.getLastName() ) );
    lastNameField = new TextBox();
    lastNameField.setText( data.getLastName() );
    addChild( lastNameField );

    addChild( new Label( resourceData.getStatus() ) );
    statusField = new TextBox();
    statusField.setText( data.getStatus() );
    addChild( statusField );

    addChild( new Label( resourceData.getPhone() ) );
    phoneField = new TextBox();
    phoneField.setText( data.getPhone() );
    addChild( phoneField );

    addChild( new Label( resourceData.getEmail() ) );
    emailField = new TextBox();
    emailField.setText( data.getEmail() );
    addChild( emailField );
    
    ButtonUI<SearchUserData> searchButton = new ButtonUI<SearchUserData>( data.getSearchButtonData() );

    SearchUserEvent event = new SearchUserEvent()
    {
      private static final long serialVersionUID = 8531693903689658870L;

      @Override
      public SearchUserData getData()
      {
        updateData();
        return SearchUserUI.this.getData();
      }
    };
    
    searchButton.addClickEvent( event );
  }
  
  /*
   * get the data from UI
   */
  protected void updateData()
  {
    SearchUserData theData = getData();
    theData.setName( nameField.getText() );
    theData.setFirstName( firstNameField.getText() );
    theData.setLastName( lastNameField.getText() );
    theData.setStatus( statusField.getText() );
    theData.setPhone( phoneField.getText() );
    theData.setEmail( emailField.getText() );
  }
  
}
