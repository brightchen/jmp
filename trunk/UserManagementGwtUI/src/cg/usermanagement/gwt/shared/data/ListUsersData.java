package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;
import java.util.List;

import cg.contentdata.shared.UIContentData;

public class ListUsersData extends UIContentData< ListUsersResourceData > implements Serializable
{
  private static final long serialVersionUID = 8334127934391556654L;
  
  private List< UserListData > userDatas;

  public List< UserListData > getUserDatas()
  {
    return userDatas;
  }

  public void setUserDatas( List< UserListData > userDatas )
  {
    this.userDatas = userDatas;
  }
  
  

}
