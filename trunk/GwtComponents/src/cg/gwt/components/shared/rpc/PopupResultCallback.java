package cg.gwt.components.shared.rpc;

import cg.gwt.components.client.ui.components.SimpleMessageDialogUI;

public class PopupResultCallback<T> extends PopupFailureReasonCallback<T>
{
  public static PopupResultCallback instance = new PopupResultCallback();
  
  private PopupResultCallback(){}
  
  @Override
  public void onSuccess( T result )
  {
    ( new SimpleMessageDialogUI( "success" ) ).centre();
  }

}
