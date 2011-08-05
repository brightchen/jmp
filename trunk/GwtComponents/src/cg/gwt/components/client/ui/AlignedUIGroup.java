package cg.gwt.components.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

/*
 * AlignedUIGroup is a type of UIGroup which items are aligned by column or row
 * use HTMLTable or its sub-classes as the container 
 */
public abstract class AlignedUIGroup < I > extends UIFlexTableComposite< List< ? extends I> >
{
  public AlignedUIGroup( List< ? extends I> data )
  {
    super( data );
  }
  
  public AlignedUIGroup( List<? extends I> data, FlexTable container )
  {
    this( data );
    setContainer( container );
  }
  
  @Override
  protected void beforeAddingChildren()
  {
    buildAndAddChildren( getData() );
  }
  
  /*
   * build and add children component into the children list
   * the build() method will add the component in the children list into the container
   */
  public void buildAndAddChildren( List<? extends I> data )
  {
    if( data == null || data.isEmpty() )
      return;
    
    int index = 0;
    for( I childData : data )
    {
      addChildComponent( buildChildComponent( childData, index ), index );
      ++index;
    }
  }

  protected abstract Widget buildChildComponent( I childData, int index );

}
