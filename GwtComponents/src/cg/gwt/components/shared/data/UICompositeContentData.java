package cg.gwt.components.shared.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cg.contentdata.shared.ICompositeContentData;
import cg.contentdata.shared.ResourceData;
import cg.contentdata.shared.UIContentData;

/*
 * there are a lot of strategies to get the list of list of sub-content-data
 * such as SubContentDataAnnotationLookupStrategy, SubContentDataTypeLookupStrategy, SubContentDataDefaultLookupStrategy etc.
 * but all above is using the static analysis, they can't have dynamic and override sub-content-datas well
 * this class handles the dynamic and override case
 */
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
