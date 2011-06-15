package cg.usermanagement.permission;

import java.util.HashSet;
import java.util.Set;

import cg.common.uid.IUidData;
import cg.common.uid.UidGenerator;
import cg.model.common.Feature;
import cg.model.common.IFeatureEntries;
import cg.usermanagement.UserManagementModuleInfo;

public enum UserManagementFeature implements IFeatureEntries, IUidData
{
  USER,
  ACCOUNT,
  ROLE,
  PERMISSION;

  /* 
   * convert it into global feature
   */
  public Feature toFeature()
  {
    Feature feature = new Feature();
    feature.setId( UidGenerator.getDefaultInstance().getUid( this, ordinal() ) );
    feature.setName( name() );
    return feature;
  }
  
  @Override
  public Set< Feature > getFeatures()
  {
    UserManagementFeature[] umfs = values();
    Set< Feature > features = new HashSet< Feature >();
    if( umfs == null || umfs.length == 0 )
      return features;
    for( UserManagementFeature umf : umfs )
    {
      features.add( umf.toFeature() );
    }
    return features;
  }

  @Override
  public int getModuleId()
  {
    return UserManagementModuleInfo.getInstance().getModuleId();
  }

  @Override
  public int getSectionId()
  {
    return 1;
  }
}
