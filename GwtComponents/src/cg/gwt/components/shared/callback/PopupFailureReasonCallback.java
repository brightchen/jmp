package cg.gwt.components.shared.callback;

import cg.gwt.components.client.ui.components.SimpleErrorDialogUI;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class PopupFailureReasonCallback< T > implements AsyncCallback< T >
{
  @Override
  public void onFailure( Throwable caught )
  {
    ( new SimpleErrorDialogUI(  caught.getMessage() ) ).centre();
  }

  @Override
  public void onSuccess( T result )
  {
    //do nothing by default
  }
}
