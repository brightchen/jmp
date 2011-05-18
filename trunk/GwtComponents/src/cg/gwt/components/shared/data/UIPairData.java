package cg.gwt.components.shared.data;

import java.io.Serializable;

//CompositePartData is a kind of data which composed by two PartData
public class UIPairData< D1, D2 > implements Serializable
{
  private static final long serialVersionUID = 914411604596879321L;

  private D1 data1;
  private D2 data2;
  
  public UIPairData( D1 data1, D2 data2 )
  {
    setData1( data1 );
    setData2( data2 );
  }
  
  public D1 getData1()
  {
    return data1;
  }
  public void setData1( D1 data1 )
  {
    this.data1 = data1;
  }
  public D2 getData2()
  {
    return data2;
  }
  public void setData2( D2 data2 )
  {
    this.data2 = data2;
  }

}
