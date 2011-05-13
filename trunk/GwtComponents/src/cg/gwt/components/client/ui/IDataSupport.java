package cg.gwt.components.client.ui;

/*
 * support getData()/setData()
 */
public interface IDataSupport< D > extends IDataProvider< D >
{
  public void setData( D data );
}
