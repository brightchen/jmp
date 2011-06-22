package cg.gwt.components.client.ui.decorator;

import cg.gwt.components.client.ui.components.ButtonUI;
import cg.gwt.components.client.ui.event.UIEvent;
import cg.gwt.components.shared.data.ButtonData;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class PopupWithCancelButtonDecorator< UC extends Widget > extends PopupDecorator< String, UC >
{
  public PopupWithCancelButtonDecorator( String data, UC decoratedComponent )
  {
    super( data, decoratedComponent );
  }

  @Override
  protected PopupPanel buildDecoratorComponent()
  {
    PopupPanel popup = new PopupPanel();
    popup.setTitle( getData() );
    return popup;
  }

  //add the cancel button after being added the decorated component
  @Override
  protected void afterAddingChildren()
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
                                    // TODO Auto-generated method stub
                                    return null;
                                  }
      
                                });
    getDecoratorComponent().add( cancelButton );
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
