package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.contentdata.shared.GridContentData;

public class ListUsersGridData< RD extends ListUsersResourceData > extends GridContentData< UserListData, RD > implements Serializable
{
  private static final long serialVersionUID = -4773863620107899631L;

  @Override
  public String getText( UserListData rowData, int column )
  {
    return rowData.getText( column );
  }

  @Override
  public int getColumnCount()
  {
    //use resource data's Count
    RD resourceData = getResourceData();
    if( resourceData == null )
      return 0;
    
    return resourceData.getCount();
  }

  @Override
  public String getHeaderText( int column )
  {
    // header text should get from the resource data;
    RD resourceData = getResourceData();
    if( resourceData == null )
      return "";
    
    return resourceData.getText( column );
  }
}
