package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.contentdata.shared.GridContentData;
import cg.contentdata.shared.ResourceData;

public class ListUsersGridData< RD extends ResourceData > extends GridContentData< UserListData, RD > implements Serializable
{
  private static final long serialVersionUID = -4773863620107899631L;

  @Override
  public int getColumnCount()
  {
    return 7;
  }

  @Override
  public String getText( UserListData rowData, int column )
  {
    return rowData.getText( column );
  }

  @Override
  public String getHeaderText( int column )
  {
    // TODO Auto-generated method stub
    return null;
  }

}
