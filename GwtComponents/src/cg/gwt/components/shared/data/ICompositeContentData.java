package cg.gwt.components.shared.data;

import java.util.List;

public interface ICompositeContentData
{
  @SuppressWarnings( "rawtypes" ) 
  public List< ? extends UIContentData > getSubContentDatas();
}
