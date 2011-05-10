package cg.gwt.components.client.ui.old;

import cg.gwt.components.shared.data.CompositeWidgetData;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

//the concept of Composite here is a little bit different from the GWT API
//here the Composite means the Widget contains at least two widgets
//the Composite in GWT API is more likely a decorator, a widget can attach/detach to/from it
//CompositePart is a kind of part which composed by two parts
//in GWT, Panel the widget which can contain other widget
public abstract class CompositeBuilder< D1, W1 extends Widget, B1 extends UIObjectBuilder< D1, W1 >,
                                        D2, W2 extends Widget, B2 extends UIObjectBuilder< D2, W2 >, 
                                        C extends Panel > 
      extends UIObjectBuilder< CompositeWidgetData< D1, D2>, C >
{
  private B1 builder1;
  private B2 builder2;
  
  //as each part include its data, the composite itself don't have to keep the data
  //just reference to each part's data
  @Override
  public void setData( CompositeWidgetData<D1, D2> data )
  {
    if( builder1 != null )
    {
      builder1.setData( data.getData1() );
    }
    if( builder2 != null )
    {
      builder2.setData( data.getData2() );
    }
  }

  //get data from each part and compose for output
  @Override 
  public CompositeWidgetData<D1, D2> getData()
  {
    CompositeWidgetData<D1, D2> data = createData();
    if( builder1 != null )
      data.setData1( builder1.getData() );
    if( builder2 != null )
      data.setData2( builder2.getData() );
    return data;
  }
  
//  @Override
//  public void updateData()
//  {
//    if( part1 != null )
//      part1.updateData();
//    if( part2 != null )
//      part2.updateData();
//  }

  @Override
  protected CompositeWidgetData<D1, D2> createData()
  {
    return new CompositeWidgetData<D1, D2>();
  }

  public B1 getBuilder1()
  {
    return builder1;
  }
  public void setBuilder1( B1 builder1 )
  {
    this.builder1 = builder1;
  }

  public B2 getBuilder2()
  {
    return builder2;
  }
  public void setBuilder2( B2 builder2 )
  {
    this.builder2 = builder2;
  }

}
