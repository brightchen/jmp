package cg.gwt.components.client.ui.components;

import com.google.gwt.user.client.ui.Button;

import cg.gwt.components.client.ui.UIComponent;
import cg.gwt.components.shared.data.ButtonData;

public class ButtonUI extends UIComponent< ButtonData, Button >
{
  private Button button;
  
  public ButtonUI( ButtonData data )
  {
    setData( data );
  }
  
  @Override
  public Button build()
  {
    ButtonData data = getData();
    button = new Button();
    button.setText( data.getTitle() );
    button.setEnabled( data.isEnabled() );
    return button;
  }

  @Override
  public Button getRealComponent()
  {
    return button;
  }

}
