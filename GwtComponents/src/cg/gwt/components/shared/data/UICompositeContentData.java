package cg.gwt.components.shared.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UICompositeContentData< D extends ResourceData > extends UIContentData< D >  implements ICompositeContentData, Serializable
{
  private static final long serialVersionUID = -6910434103700384971L;

  private List< UIContentData<?> > subContentDatas = new ArrayList< UIContentData<?> >();

  public UICompositeContentData()
  {
  }
  
  public List< UIContentData< ? >> getSubContentDatas()
  {
    return subContentDatas;
  }

  public void setSubContentDatas( List< UIContentData< ? >> subContentDatas )
  {
    this.subContentDatas = subContentDatas;
  }
  
  public void addSubContentData( UIContentData<?> subContentData )
  {
    subContentDatas.add( subContentData );
  }
}
