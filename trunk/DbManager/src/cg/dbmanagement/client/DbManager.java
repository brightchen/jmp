package cg.dbmanagement.client;

import cg.usermanagement.gwt.client.LoginHandler;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DbManager implements EntryPoint
{
  /**
   * This is the entry point method.
   */
  public void onModuleLoad()
  {
    TabPanel tp = new TabPanel();
    tp.add(new HTML("System"), "System");
    tp.add(new HTML("Database"), "Database");

    // Show the 'bar' tab initially.
    tp.selectTab(1);

    // Add it to the root panel.
    RootPanel.get().add(tp);

  }
  
  protected Widget buildSystemLogin()
  {
    FlexTable table = new FlexTable();

    table.setText(0, 0, "system user name");
    final TextBox nameField = new TextBox();
    nameField.setText( "" );
    nameField.setFocus( true );
    nameField.selectAll();
    table.setWidget( 1, 0, nameField );
    
    table.setText(0, 1, "password");
    final TextBox passwordField = new TextBox();
    passwordField.setText( "" );
    table.setWidget( 1, 1, passwordField );

    //login button
    {
      final Button loginButton = new Button("Login");
      // Add a handler to send the name to the server
      LoginHandler handler = new LoginHandler( nameField, passwordField );
      loginButton.addClickHandler( handler );
      loginButton.addKeyUpHandler( handler );
  
      table.setWidget(1, 2, loginButton);
    }
    return table;
  }
  
  protected Widget buildDatabaseLogin()
  {
    FlexTable table = new FlexTable();

    table.setText(0, 0, "database user name");
    final TextBox nameField = new TextBox();
    nameField.setText( "" );
    nameField.setFocus( true );
    nameField.selectAll();
    table.setWidget( 1, 0, nameField );
    
    table.setText(0, 1, "password");
    final TextBox passwordField = new TextBox();
    passwordField.setText( "" );
    table.setWidget( 1, 1, passwordField );

    //login button
    {
      final Button loginButton = new Button("Login");
      // Add a handler to send the name to the server
      LoginHandler handler = new LoginHandler( nameField, passwordField );
      loginButton.addClickHandler( handler );
      loginButton.addKeyUpHandler( handler );
  
      table.setWidget(1, 2, loginButton);
    }
    return table;
  }
  
}
