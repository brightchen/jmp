package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.annotation.IContentDataAttributes;
import cg.gwt.components.annotation.IContentDataIndicator;
import cg.gwt.components.server.resource.SubContentDataAnnotationLookupStrategy;
import cg.gwt.components.shared.data.ButtonData;
import cg.gwt.components.shared.data.UIContentData;
import cg.resourcemanagement.annotation.IResourceKey;

@IContentDataAttributes( isComposite = true, subContentDataLookupStrategy = SubContentDataAnnotationLookupStrategy.class )
public class AddRoleData extends UIContentData< AddRoleResourceData > implements Serializable
{
  private static final long serialVersionUID = -6940977542611194133L;

  private Long id;        //role id;
  private String name;    //role name
  
  //add or update
  private ButtonData saveButtonData;
  
  public AddRoleData(){}
  
  public Long getId()
  {
    return id;
  }
  public void setId( Long id )
  {
    this.id = id;
  }
  public String getName()
  {
    return name;
  }
  public void setName( String name )
  {
    this.name = name;
  }

  public ButtonData getSaveButtonData()
  {
    return saveButtonData;
  }

  @IContentDataIndicator( isContentData = true )  //the saveButtonData is sub-content-data
  @IResourceKey( inheritModuleName = true, moduleName = "um", className = "saverolebutton" )  //the className of this ButtonData is saverolebutton
  public void setSaveButtonData( ButtonData saveButtonData )
  {
    this.saveButtonData = saveButtonData;
  }
}
