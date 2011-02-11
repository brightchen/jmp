package cg.gwt.components.client.ui;

import cg.gwt.components.shared.data.CompositePartData;
import cg.gwt.components.shared.data.PartData;

import com.google.gwt.user.client.ui.Widget;

//CompositePart is a kind of part which composed by two parts
public abstract class CompositePart< D1 extends PartData, W1 extends Widget, P1 extends Part< D1, W1 >,
                                     D2 extends PartData, W2 extends Widget, P2 extends Part< D2, W2 >, 
                                     W extends Widget > 
    extends Part< CompositePartData< D1, D2>, W >
{
  public void setData( D1 data1, D2 data2 )
  {
    CompositePartData< D1, D2> data = new CompositePartData< D1, D2>();
    data.setData1( data1 );
    data.setData2( data2 );
    setData( data );
  }

}
