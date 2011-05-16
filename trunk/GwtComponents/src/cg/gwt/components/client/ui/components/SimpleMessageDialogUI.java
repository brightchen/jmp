package cg.gwt.components.client.ui.components;

import cg.gwt.components.client.ui.decorator.PopupDecorator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SimpleMessageDialogUI extends PopupDecorator< String, Label >
{
  public SimpleMessageDialogUI( String message )
  {
    super( message );
  }
  
  // build child before adding
  @Override
  protected void beforeAddingChildren()
  {
    setTitle();
    
    Panel messagePanel = new VerticalPanel();
    if( getData() != null )
    {
      Label messageLabel = new Label( getData() );
      messagePanel.add( messageLabel );
    }
    
    Button okButton = new Button( "OK", new ClickHandler()
    {
      @Override
      public void onClick( ClickEvent event )
      {
        hide( true );
      }
    } );
    
    messagePanel.add( okButton );
  }
  
  protected void setTitle()
  {
    getDecoratorComponent().setTitle( "Message" );
  }
  
  @Override
  protected PopupPanel buildDecoratorComponent()
  {
    return new DialogBox( false );
  }
  
}
