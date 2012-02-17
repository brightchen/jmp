package cg.model.util;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cg.common.reflect.ReflectionsBuilder;
import cg.model.common.IPermissionEntries;
import cg.model.common.Permission;

public class PermissionManager implements Serializable
{
  private static final long serialVersionUID = 3842989715690883800L;

  private static PermissionManager instance;
  private Set< Permission > permissions = new HashSet< Permission >();
  
  private PermissionManager()
  {
  }
  
  public static PermissionManager getInstance()
  {
    if ( instance == null )
    {
      synchronized ( PermissionManager.class )
      {
        if ( instance == null )
        {
          instance = new PermissionManager();
          instance.registerPermissions();
        }
      }
    }

    return instance;
  }

  protected static void addPermission( Permission permission )
  {
    getInstance().permissions.add( permission );
  }

  protected static void addPermissions( Set< Permission > thePermission )
  {
    getInstance().permissions.addAll( thePermission );
  }


  /*
   * find features and register them
   */
  protected void registerPermissions()
  {
    Set< Class< ? extends IPermissionEntries > > permissionEntriesClasses = (new ReflectionsBuilder()).buildSubTypeReflections().getSubTypesOf( IPermissionEntries.class );
    if( permissionEntriesClasses == null || permissionEntriesClasses.size() == 0 )
    {
      return;
    }
    
    for( Class< ? extends IPermissionEntries > entriesClass : permissionEntriesClasses )
    {
      registerPermissions( entriesClass );
    }
  }
  

  protected void registerPermissions( Class< ? extends IPermissionEntries > entriesClass )
  {
    try
    {
      IPermissionEntries entries = entriesClass.newInstance();
      addPermissions( entries.getPermissions() );
    }
    catch( Exception e )
    {
      throw new RuntimeException( "Permission Entries class " + entriesClass + " do not support default constructor.", e );
    }
  }

}
