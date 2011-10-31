package cg.gwt.components.shared.data;

import java.io.Serializable;
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
public class FrameData implements Serializable
{
  private static final long serialVersionUID = -4472676609382823228L;
  
  private Frame frame;    //don't allow to change the frame
  private List< ResponseData<?> > responseDatas;
  //cache this
  private Set<UIIdentity> responseIdentities;
  
  public FrameData(){}
  
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
   * merge the response data into FrameData, the rd will be added to frame data only if there don't have 
   * other response data in the frame data with same UIIdentity
   * @param rd
   */
  public void mergeResponseData( ResponseData<?> rd )
  {
    if( !isAccept( rd ) )
      return;
    
    UIIdentity identity = rd.getFlowData().getUiIdentity();
    for( ResponseData<?> responseData : responseDatas )
    {
      if( responseData.getFlowData().getUiIdentity().equals( identity ) )
        return;
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
    return getFrameAllowedIdentities().contains( rd.getFlowData().getUiIdentity() );
  }
  
  public Set<UIIdentity> getFrameAllowedIdentities()
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
  
  /**
   * the data of this FrameData over the data of other FrameData
   * @param otherFrameData
   */
  public void merger( List< ResponseData<?> > otherResponseDatas )
  {
    if( otherResponseDatas == null )
      return;

    for( ResponseData<?> otherRd : otherResponseDatas )
    {
      mergeResponseData( otherRd );
    }
  }

  public Frame getFrame()
  {
    return frame;
  }

  protected void setFrame( Frame frame )
  {
    this.frame = frame;
  }
  
}
