package cg.gwt.components.shared.data;


public enum Frame
{
  UMF_START( UIIdentity.CONTROL_SECTION, UIIdentity.UM_START ), 
  UMF_CONTROL_PANEL( UIIdentity.CONTROL_SECTION, UIIdentity.UM_CONTROL_PANEL ),

  UMF_SEARCH_USER( UMF_CONTROL_PANEL, UIIdentity.UM_SEARCH_USER ), 
  UMF_LIST_USERS( UMF_SEARCH_USER, UIIdentity.UM_LIST_USERS ),

  UMF_SEARCH_ACCOUNT( UMF_CONTROL_PANEL, UIIdentity.UM_SEARCH_ACCOUNT ),

  UMF_SEARCH_ROLE( UMF_CONTROL_PANEL, UIIdentity.UM_SEARCH_ROLE ),

  UMF_ADD_ROLE( UMF_CONTROL_PANEL, UIIdentity.UM_ADD_ROLE ),

  UMF_ADD_PERMISSION( UMF_CONTROL_PANEL, UIIdentity.UM_ADD_PERMISSION ), 
  
  ;
  private UIIdentity[] uiIdentities;

  /**
   * this frame can be defined by one frame add other uis
   * 
   * @param frame
   * @param uiIdentities
   */
  private Frame( Frame frame, UIIdentity... uiIdentities )
  {
    if ( uiIdentities == null )
    {
      this.uiIdentities = frame.getUiIdentities();
      return;
    }

    UIIdentity[] frameUIs = frame.getUiIdentities();
    int frameUILength = frameUIs.length;
    int length = frame.getUiIdentities().length + uiIdentities.length;
    this.uiIdentities = new UIIdentity[length];

    int index;
    // copy identities from fame;
    for ( index = 0; index < frameUIs.length; ++index )
    {
      this.uiIdentities[index] = frameUIs[index];
    }
    // copy identities from uiIdentities
    for ( ; index < length; ++index )
    {
      this.uiIdentities[index] = uiIdentities[index - frameUILength];
    }
  }

  /**
   * this frame define by uis
   * 
   * @param uiIdentities
   */
  private Frame( UIIdentity... uiIdentities )
  {
    this.uiIdentities = uiIdentities;
  }

  public UIIdentity[] getUiIdentities()
  {
    return uiIdentities;
  }
}
