package cg.usermanagement.permission;

import java.util.HashSet;
import java.util.Set;

import cg.model.common.Feature;
import cg.model.common.IFeatureEntries;

public enum UserManagementFeature implements IFeatureEntries
{
  USER,
  ACCOUNT,
  ROLE,
  PERMISSION;

  @Override
  public Set< Feature > getFeatures()
  {
    UserManagementFeature[] umfs = values();
    Set< Feature > features = new HashSet< Feature >();
    if( umfs == null || umfs.length == 0 )
      return features;
    for( UserManagementFeature umf : umfs )
    {
      Feature feature = new Feature();
      feature.setId( (long)umf.ordinal() );
      feature.setName( umf.name() );
      features.add( feature );
    }
    return features;
  }
}
