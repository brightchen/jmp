package cg.gwt.components.client.ui;

//The UIObject can be build by it
public interface IBuildable< D >
{
  public void build();
  public D getData();
}
