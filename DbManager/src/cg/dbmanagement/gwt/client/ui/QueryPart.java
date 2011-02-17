package cg.dbmanagement.gwt.client.ui;

import cg.dbmanagement.gwt.shared.data.QueryInputData;
import cg.dbmanagement.gwt.shared.data.QueryResultData;
import cg.gwt.components.client.ui.TypicalCompositePart;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

public class QueryPart extends TypicalCompositePart< QueryInputData, FlexTable, QueryInputPart,
                                                     QueryResultData, FlexTable, QueryResultPart, VerticalPanel >
{
  public QueryPart( QueryInputPart part1, QueryResultPart part2 )
  {
    super( part1, part2 );
    part1.setSqlOutputHandler( part2 );
  }
}
