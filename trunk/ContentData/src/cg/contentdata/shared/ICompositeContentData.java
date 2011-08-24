package cg.contentdata.shared;

import java.util.List;


public interface ICompositeContentData
{
  @SuppressWarnings( "rawtypes" ) 
  public List< ? extends UIContentData > getSubContentDatas();
}
