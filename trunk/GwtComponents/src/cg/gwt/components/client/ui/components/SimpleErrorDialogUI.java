package cg.gwt.components.client.ui.components;



public class SimpleErrorDialogUI extends SimpleMessageDialogUI
{
  public SimpleErrorDialogUI( String errorMessage )
  {
    super( errorMessage );
  }

  @Override
  protected void setTitle()
  {
    getDecoratorComponent().setTitle( "Error" );
  }

}
