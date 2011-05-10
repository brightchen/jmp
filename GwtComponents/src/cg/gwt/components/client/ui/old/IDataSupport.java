package cg.gwt.components.client.ui.old;

/*
 * support getData()/setData()
 */
public interface IDataSupport< D >
{
  public void setData( D data );
  public D getData();
}
