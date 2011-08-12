package cg.gwt.components.shared.data;

import java.io.Serializable;

/*
 * the data response from web server to the client side, it includes
 *   - the content data: the data required to build the next content UI
 *   - the flow data: the data required to control the UI flow, namely, what's the next UI should be
 */
@SuppressWarnings( "rawtypes" ) 
public class ResponseData< D extends UIContentData > implements Serializable
{
  private static final long serialVersionUID = 1062504170687810293L;

  // the data used to draw the content UI
  private D contentData;
  
  // the data used to manage the UI flow
  private UIFlowData flowData;

  public ResponseData(){}
  public ResponseData( D contentData, UIFlowData flowData )
  {
    setContentData( contentData );
    setFlowData( flowData );
  }
  
  public D getContentData()
  {
    return contentData;
  }

  public void setContentData( D contentData )
  {
    this.contentData = contentData;
  }

  public UIFlowData getFlowData()
  {
    return flowData;
  }

  public void setFlowData( UIFlowData flowData )
  {
    this.flowData = flowData;
  }
  
  public void setFlowData( UIIdentity uiIdentity )
  {
    setFlowData( new UIFlowData( uiIdentity ) );
  }
  
  
}
