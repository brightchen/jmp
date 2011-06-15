package cg.usermanagement.gwt.client.register;

import cg.gwt.components.client.ui.components.SimpleErrorDialogUI;
import cg.gwt.components.client.ui.components.SimpleMessageDialogUI;
import cg.gwt.components.client.ui.event.UIEvent;
import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.usermanagement.gwt.client.IUserManagement;
import cg.usermanagement.gwt.client.IUserManagementAsync;
import cg.usermanagement.gwt.shared.data.UserRegisterData;
import cg.usermanagement.shared.RegisterUserException;

import com.google.gwt.core.client.GWT;

public abstract class UserRegisterEvent extends UIEvent< UserRegisterData >
{
  private IUserManagementAsync userService = GWT.create( IUserManagement.class );
  
  @Override
  public void fire()
  {
    registerUser();
  }

  protected void registerUser()
  {
    userService.registerUser( getData(), 
                       new PopupFailureReasonCallback< Void >()
                        {
                          @Override
                          public void onFailure( Throwable caught )
                          {
                            if( !( caught instanceof RegisterUserException ) )
                            {
                              ( new SimpleErrorDialogUI( caught.getMessage() ) ).centre();
                            }
                            else
                            {
                              RegisterUserException e = (RegisterUserException)caught;
                              ( new SimpleErrorDialogUI( e.getErrorReason() ) ).centre();
                            }
                          }

                          @Override
                          public void onSuccess( Void returned )
                          {
                            onRegisterUserSuccess();
                          }
                        } );
  }

  protected void onRegisterUserSuccess()
  {
    (new SimpleMessageDialogUI( "register user successful."  )).show( );
  }
}
