package cg.dbmanagement.gwt.client.ui;

import cg.dbmanagement.gwt.shared.data.QueryInputData;
import cg.dbmanagement.gwt.shared.data.QueryResultData;
import cg.gwt.components.client.ui.TypicalCompositeBuilder;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class QueryPart extends TypicalCompositeBuilder< QueryInputData, FlexTable, QueryInputWidgetBuilder,
                                                     QueryResultData, ScrollPanel, QueryResultWidgetBuilder, VerticalPanel >
{
  public QueryPart( QueryInputWidgetBuilder part1, QueryResultWidgetBuilder part2 )
  {
    super( part1, part2 );
    part1.setSqlOutputHandler( part2 );
  }
  
  //add the actions for the whole QueryPart
  @Override
  public VerticalPanel build()
  {
    HorizontalPanel actionsPanel = new HorizontalPanel();
    Button closeButton = new Button( "Close" );
    closeButton.addClickHandler( new ClickHandler()
                                  {
                                    @Override
                                    public void onClick( ClickEvent event )
                                    {
                                      doClose();
                                    }
                              
                                  } );
    actionsPanel.add( closeButton );

    VerticalPanel panel = getContainer();
    panel.add( actionsPanel );
    
    return super.build();
  }
  
  protected void doClose(){}
}
