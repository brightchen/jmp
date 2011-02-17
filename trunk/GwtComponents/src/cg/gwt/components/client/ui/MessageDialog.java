package cg.gwt.components.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;

public class MessageDialog
{
  private String title;
  
  public MessageDialog()
  {
    setTitle( "Message" );
  }
  
  public void displayMessage( String message )
  {
    final DialogBox dlg = new DialogBox();
    dlg.setTitle( getTitle() );
    dlg.setText( message );
    
    Button okButton = new Button( "OK", new ClickHandler()
    {
      @Override
      public void onClick( ClickEvent event )
      {
        dlg.hide();
      }
    } );
    
    dlg.add( okButton );
    
    dlg.center();
  }
  
  public void setTitle( String title )
  {
    this.title = title;
  }
  public String getTitle()
  {
    return title;
  }
}
