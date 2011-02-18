package cg.gwt.components.client.ui;

import cg.gwt.components.shared.data.CompositeWidgetData;
import cg.gwt.components.shared.data.WidgetData;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

//the concept of Composite here is a little bit different from the GWT API
//here the Composite means the Widget contains at least two widgets
//the Composite in GWT API is more likely a decorator, a widget can attach/detach to/from it
//CompositePart is a kind of part which composed by two parts
//in GWT, Panel the widget which can contain other widget
public abstract class CompositeBuilder< D1 extends WidgetData, W1 extends Widget, P1 extends Builder< D1, W1 >,
                                        D2 extends WidgetData, W2 extends Widget, P2 extends Builder< D2, W2 >, 
                                        P extends Panel > 
    extends Builder< CompositeWidgetData< D1, D2>, P >
{
  private P1 part1;
  private P2 part2;
  
  //as each part include its data, the composite itself don't have to keep the data
  //just reference to each part's data
  @Override
  public void setData( CompositeWidgetData<D1, D2> data )
  {
    if( part1 != null )
    {
      part1.setData( data.getData1() );
    }
    if( part2 != null )
    {
      part2.setData( data.getData2() );
    }
  }

  //get data from each part and compose for output
  @Override 
  public CompositeWidgetData<D1, D2> getData()
  {
    CompositeWidgetData<D1, D2> data = createData();
    if( part1 != null )
      data.setData1( part1.getData() );
    if( part2 != null )
      data.setData2( part2.getData() );
    return data;
  }
  
  @Override
  public void updateData()
  {
    if( part1 != null )
      part1.updateData();
    if( part2 != null )
      part2.updateData();
  }

  @Override
  protected CompositeWidgetData<D1, D2> createData()
  {
    return new CompositeWidgetData<D1, D2>();
  }

  public P1 getPart1()
  {
    return part1;
  }

  public void setPart1( P1 part1 )
  {
    this.part1 = part1;
  }

  public P2 getPart2()
  {
    return part2;
  }

  public void setPart2( P2 part2 )
  {
    this.part2 = part2;
  }
}
