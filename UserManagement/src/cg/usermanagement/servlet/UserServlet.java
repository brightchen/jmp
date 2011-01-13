package cg.usermanagement.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cg.uia.component.ReferenceData;
import cg.uia.components.RawText;
import cg.uia.renderer.IComponentRenderer;
import cg.uia.renderer.lookup.RendererLookup;

public class UserServlet extends HttpServlet
{
  private static final long serialVersionUID = -7913184884247300155L;

  @Override
  protected void doGet( HttpServletRequest req, HttpServletResponse resp )
  {
    handleUserRequest( req, resp );
  }
  
  protected void doPost( HttpServletRequest req, HttpServletResponse resp )
  {
    handleUserRequest( req, resp );
  }
  
  protected void handleUserRequest( HttpServletRequest req, HttpServletResponse resp )
  {
    RawText rawText = new RawText();
    rawText.setData( new ReferenceData< String >( "This is the user service." ) );
    IComponentRenderer renderer = RendererLookup.getInstance().lookup( rawText );
    try
    {
      renderer.render( resp.getWriter(), rawText );
    }
    catch( Exception e )
    {
      
    }
  }
  
}
