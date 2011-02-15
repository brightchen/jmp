package cg.gwt.components.shared.callback;

import cg.gwt.components.client.ui.ErrorDialog;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class PopupFailReasonCallback implements AsyncCallback
{
   @Override
   public void onFailure( Throwable caught )
   {
     ( new ErrorDialog() ).displayMessage( caught.getMessage() );
   }
}
