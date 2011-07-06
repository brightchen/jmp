package cg.gwt.components.shared.data;


public class NormalMenuItemData extends MenuItemData
{
  private static final long serialVersionUID = -8086856314308266592L;
  
  private String title;
  private String commandKey;
  
  public NormalMenuItemData()
  {
    this( null );
  }
  
  public NormalMenuItemData( String title )
  {
    this( null, null );
  }

  public NormalMenuItemData( String title, String commandKey )
  {
    super( MenuItemType.NORMAL );
    setTitle( title );
    setCommandKey( commandKey );
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle( String title )
  {
    this.title = title;
  }

  public String getCommandKey()
  {
    return commandKey;
  }

  public void setCommandKey( String commandKey )
  {
    this.commandKey = commandKey;
  }
  
  
}
