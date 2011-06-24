package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

public class UIFlowData implements Serializable
{
  private static final long serialVersionUID = 2521545450694219040L;

  private UIIdentity uiIdentity;    //which UI should to display

  public UIFlowData(){}
  
  public UIFlowData( UIIdentity uiIdentity )
  {
    setUiIdentity( uiIdentity );
  }
  
  public UIIdentity getUiIdentity()
  {
    return uiIdentity;
  }

  public void setUiIdentity( UIIdentity uiIdentity )
  {
    this.uiIdentity = uiIdentity;
  }

  
  
}
