package cg.uia.renderer.lookup;

import java.util.Arrays;
import java.util.List;

import cg.uia.api.IComponent;
import cg.uia.renderer.IComponentRenderer;

// use the default renderer with class: <Component> + Renderer, for example RawText --> RawTextRenderer
//FIXME: there are potential thread-safe problem for adding/modifying entry to the map and finding 
public class DefaultRendererMatchStrategy implements IRendererMatchStrategy
{
  private static DefaultRendererMatchStrategy instance;
  private static Package[] defaultRenderersPackages = null;
  
  private List< Package > renderersPackages = Arrays.asList( defaultRenderersPackages );

  protected static void initClass()
  {
    defaultRenderersPackages = new Package[1];
    defaultRenderersPackages[0] = Package.getPackage( "cg.uia.renderers" ); 
  }
  
  public static DefaultRendererMatchStrategy getInstance()
  {
    if( instance != null )
    {
      return instance;
    }
    
    synchronized( DefaultRendererMatchStrategy.class )
    {
      if( instance != null )
        return instance;
      initClass();
      instance = new DefaultRendererMatchStrategy();
      return instance;
    }
  }

  private DefaultRendererMatchStrategy()
  {
  }

  @SuppressWarnings( "unchecked" )
  @Override
  public < T extends IComponent< ? >> IComponentRenderer< T > findMatchedRenderer( T component )
  {
    String componentClassName = component.getClass().getSimpleName();
    String[] rendererClassNames = getRendererClassNames( componentClassName ); 
    
    for( String rendererClassName : rendererClassNames )
    {
      try
      {
        Class<?> rendererClass = Class.forName( rendererClassName );
        Object renderer = rendererClass.newInstance();
        return ( renderer instanceof IComponentRenderer ) ? (IComponentRenderer<T>)renderer : null;
      }
      catch( Exception e )
      {
        return null;
      }
    }
    return null;
  }
  
  private String[] getRendererClassNames( String componentClassName )
  {
    if( renderersPackages.size() == 0 )
      return null;
    String rendererClassName = componentClassName + "Renderer";
    String[] rendererClassNames = new String[ renderersPackages.size() ];
    int index = 0;
    for( Package renderersPackage : renderersPackages )
    {
      rendererClassNames[ index++ ] = renderersPackage.getName() + "." + rendererClassName;
    }
    return rendererClassNames;
  }
  
  public List< Package > getRenderersPackages()
  {
    return renderersPackages;
  }
  public void setRenderersPackages( List< Package > renderersPackages )
  {
    this.renderersPackages = renderersPackages;
  }
  public void setRenderersPackage( List< String > renderersPackageNames )
  {
    this.renderersPackages.clear();
    for( String packageName : renderersPackageNames )
    {
      this.renderersPackages.add( Package.getPackage( packageName ) );
    }
  }
  public void addRenderererPackage( Package rendererPackage )
  {
    renderersPackages.add( rendererPackage );
  }
  
  
}
