package cg.gwt.components.shared.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * this class represents a frame of response, which wrapper the List< ResponseData<?> >
 * the FrameResponsesData should not contain the response which shouldn't contain. but it don't have to contain all responses
 * it only need to contain the response which need to be updated
 * @author bchen
 *
 */
public class FrameData
{
  private Frame frame;    //don't allow to change the frame
  private List< ResponseData<?> > responseDatas;
  //cache this
  private Set<UIIdentity> responseIdentities;
  
  public FrameData( Frame frame )
  {
    this.frame = frame;
  }

  public FrameData( Frame frame, List< ResponseData<?> > responseDatas )
  {
    this( frame );
    setResponseDatas( responseDatas );
  }
  
  public List< ResponseData< ? >> getResponseDatas()
  {
    return responseDatas;
  }

  public void setResponseDatas( List< ResponseData< ? >> responseDatas )
  {
    this.responseDatas = responseDatas;
  }
  
  public void addResponseData( ResponseData<?> rd )
  {
    if( !isAccept( rd ) )
      return;
    if( responseDatas == null )
    {
      responseDatas = new ArrayList< ResponseData<?> >();
    }
    responseDatas.add( rd );
  }
  
  /**
   * is rd:ResponseData accept by this frame
   * @param rd
   * @return
   */
  public boolean isAccept( ResponseData rd )
  {
    if( rd == null )
      return false;
    return getFrameIdentities().contains( rd.getFlowData().getUiIdentity() );
  }
  
  public Set<UIIdentity> getFrameIdentities()
  {
    if( responseIdentities == null )
    {
      responseIdentities = new HashSet<UIIdentity>();
      UIIdentity[] identities = frame.getUiIdentities();
      if( identities != null && identities.length > 0 )
      {
        for( UIIdentity identity : identities )
        {
          responseIdentities.add( identity );
        }
      }
    }
    
    return responseIdentities;
  }
}
