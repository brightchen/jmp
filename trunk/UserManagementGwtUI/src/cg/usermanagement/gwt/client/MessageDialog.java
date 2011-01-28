package cg.usermanagement.gwt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;

public class MessageDialog
{
  public void displayMessage( String message )
  {
    final DialogBox dlg = new DialogBox();
    dlg.setTitle( "Message" );
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
    
    dlg.show();
  }
}
