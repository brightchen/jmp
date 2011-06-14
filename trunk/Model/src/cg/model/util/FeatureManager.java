package cg.model.util;

import java.util.HashSet;
import java.util.Set;

import cg.common.reflect.ReflectionsBuilder;
import cg.model.common.Feature;
import cg.model.common.IFeatureEntries;

public class FeatureManager
{
  private static final long serialVersionUID = -4481808205759238923L;
  
  private static FeatureManager instance;
  
  private Set< Feature > features = new HashSet< Feature >();

  private FeatureManager()
  {
  }
  
  public static FeatureManager getInstance()
  {
    if ( instance == null )
    {
      synchronized ( FeatureManager.class )
      {
        if ( instance == null )
        {
          instance = new FeatureManager();
          instance.registerFeatures();
        }
      }
    }

    return instance;
  }
  
  public static void addFeature( Feature feature )
  {
    getInstance().features.add( feature );
  }

  public static void addFeatures( Set< Feature > theFeatures )
  {
    getInstance().features.addAll( theFeatures );
  }

  /*
   * find features and register them
   */
  protected void registerFeatures()
  {
    Set< Class< ? extends IFeatureEntries > > featureEntriesClasses = (new ReflectionsBuilder()).buildSubTypeReflections().getSubTypesOf( IFeatureEntries.class );
    if( featureEntriesClasses == null || featureEntriesClasses.size() == 0 )
    {
      return;
    }
    
    for( Class< ? extends IFeatureEntries > entriesClass : featureEntriesClasses )
    {
      registerFeatures( entriesClass );
    }
  }
  

  protected void registerFeatures( Class< ? extends IFeatureEntries > entriesClass )
  {
    try
    {
      IFeatureEntries entries = entriesClass.newInstance();
      addFeatures( entries.getFeatures() );
    }
    catch( Exception e )
    {
      throw new RuntimeException( "Feature Entries class " + entriesClass + " do not support default constructor.", e );
    }
  }

}
