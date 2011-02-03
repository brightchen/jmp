package cg.usermanagement.gwt.client;

import cg.usermanagement.gwt.client.ui.SystemUserLoginPart;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;

public class Login implements EntryPoint
{
  public void onModuleLoad()
  {
    SystemUserLoginPart loginPart = new SystemUserLoginPart();
    
    FlexTable table = loginPart.build();
    
    RootPanel rp = RootPanel.get();
    rp.add( table );
  }    

}
