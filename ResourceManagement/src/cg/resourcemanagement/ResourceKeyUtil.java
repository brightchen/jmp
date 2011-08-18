package cg.resourcemanagement;

import cg.common.util.StringUtil;

public class ResourceKeyUtil
{
  /*
   * return a new resource-key
   */
  public static ResourceKey mergeResourceKey( ResourceKey preferKey, ResourceKey key )
  {
    if( preferKey == null && key == null )
      return null;
    if( preferKey == null )
      return key.clone();
    if( key == null )
      return preferKey.clone();
    
    ResourceKey resourceKey = preferKey.clone();

    // the previous value is over the current value
    if( StringUtil.isNullOrEmpty( preferKey.getModuleName() ) && !StringUtil.isNullOrEmpty( key.getModuleName() ) )
      resourceKey.setModuleName( key.getModuleName() );
    if( StringUtil.isNullOrEmpty( preferKey.getClassName() ) && !StringUtil.isNullOrEmpty( key.getClassName() ) )
      resourceKey.setClassName( key.getClassName() );
    if( StringUtil.isNullOrEmpty( preferKey.getPropertyName() ) && !StringUtil.isNullOrEmpty( key.getPropertyName() ) )
      resourceKey.setPropertyName( key.getPropertyName() );

    return resourceKey;
  }

  
  public static boolean isValidKey( ResourceKey key )
  {
    return ( key != null && key.isValid() );
  }

}
