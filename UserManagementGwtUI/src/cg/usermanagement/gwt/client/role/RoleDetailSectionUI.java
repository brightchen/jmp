package cg.usermanagement.gwt.client.role;

import cg.gwt.components.client.ui.CompositeUI;
import cg.usermanagement.gwt.shared.data.RoleDetailData;

import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/*
 * RoleDetailSectionUI only display the role-detail information which doesn't include permissions of this role
 */
public class RoleDetailSectionUI extends CompositeUI< RoleDetailData, CaptionPanel >
{
  private TextBox roleIdInput;
  private TextBox roleNameInput;
  private HorizontalPanel panel;
  
  public RoleDetailSectionUI( RoleDetailData data )
  {
    setData( data );
    
    panel = new HorizontalPanel();
    
    addChild( new Label( data.getResourceData().getRoleId() ) );
    
    roleIdInput = new TextBox();
    roleIdInput.setText( String.valueOf( data.getId() ) );
    roleIdInput.setReadOnly( true );
    addChild( roleIdInput );
    
    addChild( new Label( data.getResourceData().getRoleName() ) );

    roleNameInput = new TextBox();
    roleNameInput.setText( data.getName() );
    addChild( roleNameInput );

  }
  
  /*
   * the CaptionPanel use SimplePanel, it doesn't allow add multiple children
   * @see cg.gwt.components.client.ui.CompositeUI#addChildToContainer(com.google.gwt.user.client.ui.Widget, com.google.gwt.user.client.ui.Widget, int)
   */
  @Override
  protected void addChildToContainer( CaptionPanel theContainer, Widget child, int index )
  {
    panel.add( child );    
  }

  @Override
  protected void afterAddingChildren()
  {
    // add the panel to caption panel
    getContainer().add( panel );
  }
  
  @Override
  protected CaptionPanel buildContainer()
  {
    return new CaptionPanel( getData().getResourceData().getRoleDetailSectionText() );
  }

  // get input role name
  public String getRoleName()
  {
    return roleNameInput.getText();
  }
}
