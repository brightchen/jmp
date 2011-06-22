package cg.gwt.components.client.ui.decorator;

import cg.gwt.components.client.ui.components.ButtonUI;
import cg.gwt.components.client.ui.event.UIEvent;
import cg.gwt.components.shared.data.ButtonData;

import com.google.gwt.user.client.ui.Widget;

public class PopupWithCancelButtonDecorator< UC extends Widget > extends PopupWithButtonBarDecorator< Widget, UC >
{
  public PopupWithCancelButtonDecorator( String title, UC decoratedComponent )
  {
    super( title, decoratedComponent );
    setButtonBar( buildButtonBar() ); 
  }
  
  
  protected Widget buildButtonBar()
  {
    ButtonUI<Void> cancelButton = new ButtonUI<Void>( getCancelButtonData() );
    cancelButton.addClickEvent( new UIEvent<Void>()
                                {
                                  @Override
                                  public void fire()
                                  {
                                    onCancelButtonClicked();
                                  }
                                  
                                  @Override
                                  public Void getData()
                                  {
                                    return null;
                                  }
      
                                });
    return cancelButton;
  }
  
  protected ButtonData getCancelButtonData()
  {
    ButtonData buttonData = new ButtonData();
    buttonData.setText( "Cancel" );
    buttonData.setTitle( "Cancel the operation and close the popup" );
    return buttonData;
  }
  
  protected void onCancelButtonClicked()
  {
    hide( true );
  }
  

}
