package cg.gwt.components.client.ui;

/*
 * support getData()/setData()
 */
public interface IDataSupport< D >
{
  public void setData( D data );
  public D getData();
}
