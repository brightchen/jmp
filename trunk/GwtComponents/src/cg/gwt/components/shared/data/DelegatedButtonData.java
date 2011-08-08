package cg.gwt.components.shared.data;

import java.io.Serializable;

/*
 * this class get button data from other object instead of keep the data as class attributes
 */
public class DelegatedButtonData extends ButtonData implements Serializable
{
  private static final long serialVersionUID = 2652338062605453339L;

  private IButtonDataSupport buttonDataSupport;
  
  public DelegatedButtonData(){}
  
  public DelegatedButtonData( IButtonDataSupport buttonDataSupport )
  {
    setButtonDataSupport( buttonDataSupport );
  }

  public IButtonDataSupport getButtonDataSupport()
  {
    return buttonDataSupport;
  }

  public void setButtonDataSupport( IButtonDataSupport buttonDataSupport )
  {
    this.buttonDataSupport = buttonDataSupport;
  }
  
  @Override
  public String getText()
  {
    return getButtonDataSupport().getButtonText();
  }
  @Override
  public String getTitle()
  {
    return getButtonDataSupport().getButtonTitle();
  }

}
