package cg.gwt.components.shared.data;

//CompositePartData is a kind of data which composed by two PartData
public class CompositePartData< I extends PartData, K extends PartData > implements PartData
{
  private static final long serialVersionUID = 914411604596879321L;

  private I data1;
  private K data2;
  
  public I getData1()
  {
    return data1;
  }
  public void setData1( I data1 )
  {
    this.data1 = data1;
  }
  public K getData2()
  {
    return data2;
  }
  public void setData2( K data2 )
  {
    this.data2 = data2;
  }

}