package cg.gwt.components.shared.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


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
  
  //only care about the client section here, as the control-section and UM_CONTROL_PANEL are always sharable.
  public static Map< Frame, Set<Frame> > frame2InheritableFrames = new HashMap< Frame, Set<Frame> >();
  static
  {
    {
      Set<Frame> inheritableFrames = new HashSet<Frame>();
      inheritableFrames.addAll( Arrays.asList( UMF_SEARCH_USER ) );
      frame2InheritableFrames.put( UMF_LIST_USERS, inheritableFrames );
    }
  }
  
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
  
  /**
   * is this frame can inherit from the otherFrame
   * inheritable means this frame don't have to rebuild if the otherFrame is the previous frame
   * @param otherFrame
   * @return
   */
  public boolean isInheritable( Frame otherFrame )
  {
    Set< Frame > inheritableFrames = frame2InheritableFrames.get( this );
    return ( inheritableFrames == null || inheritableFrames.isEmpty() ) ? false : inheritableFrames.contains( otherFrame );
  }
  
  /**
   * get the UI identities other than CONTROL_SECTION/UMF_CONTROL_PANEL
   * @return
   */
//  public UIIdentity[] getClientSectionUiIdentities()
//  {
//    UIIdentity[] identities = getUiIdentities();
//    if( identities == null || identities.length == 0 )
//      return identities;
//    
//    //simplly copy the identities;
//    UIIdentity[] csIdentities = new UIIdentity[ identities.length ];
//    int csIdentitiesCount = 0;
//    for( UIIdentity identity : identities )
//    {
//      if( UIIdentity.CONTROL_SECTION.equals( identity ) || UIIdentity.UM_CONTROL_PANEL.equals( identity ) )
//        continue;
//      csIdentities[ csIdentitiesCount++ ] = identity;
//    }
//    return Arrays.copyOfRange( csIdentities, 0, csIdentitiesCount );
//  }
}
