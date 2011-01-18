package cg.uia.api;

import java.util.List;

public interface IComposite extends IComponent
{
  public List< IComponent > getSubComponents();
  public void addComponent( IComponent component );
  //insert insertingComponent before component
//  public void insertComponent( IComponent insertingComponent, IComponent component );
  //insert insertingComponent at position <position>
  public void insertComponent( IComponent insertingComponent, int position );
}
