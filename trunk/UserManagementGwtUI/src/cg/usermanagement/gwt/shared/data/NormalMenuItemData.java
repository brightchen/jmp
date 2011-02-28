package cg.usermanagement.gwt.shared.data;

public class NormalMenuItemData extends MenuItemData
{
  private static final long serialVersionUID = -8086856314308266592L;
  
  private String title;
  private String commandKey;
  
  public NormalMenuItemData()
  {
    super( MenuItemType.NORMAL );
  }
  
  public NormalMenuItemData( String title )
  {
    this();
    setTitle( title );
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
