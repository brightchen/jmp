package cg.usermanagement.gwt.client.role;

import cg.gwt.components.client.ui.CompositeUI;
import cg.usermanagement.gwt.shared.data.RoleDetailData;

import com.google.gwt.user.client.ui.CaptionPanel;
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
  public RoleDetailSectionUI( RoleDetailData data )
  {
    setData( data );
    
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
  
  @Override
  protected void addChildToContainer( CaptionPanel theContainer, Widget child, int index )
  {
    theContainer.add( child );    
  }

  @Override
  protected CaptionPanel buildContainer()
  {
    return new CaptionPanel( getData().getResourceData().getRoleDetailSectionText() );
  }

}
