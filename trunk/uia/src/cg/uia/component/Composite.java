package cg.uia.component;

import java.util.ArrayList;
import java.util.List;

import cg.uia.api.IComponent;
import cg.uia.api.IComposite;

public class Composite extends Component implements IComposite
{
  private static final long serialVersionUID = 1663396700317006711L;

  private List< IComponent > subComponents = new ArrayList< IComponent >();
  
  @Override
  public List< IComponent > getSubComponents()
  {
    return subComponents;
  }

  @Override
  public void addComponent( IComponent component )
  {
    subComponents.add( component );
  }

  @Override
  public void insertComponent( IComponent insertingComponent, int index )
  {
    subComponents.add( index, insertingComponent );
  }

}
