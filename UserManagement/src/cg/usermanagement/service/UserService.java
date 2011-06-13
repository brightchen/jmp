package cg.usermanagement.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.transaction.annotation.Transactional;

import cg.usermanagement.api.IUserService;
import cg.usermanagement.config.PropertyKeys;
import cg.usermanagement.config.UserManagementConfigurator;
import cg.usermanagement.model.Account;
import cg.usermanagement.model.AccountRole;
import cg.usermanagement.model.Role;
import cg.usermanagement.model.RolePermission;
import cg.usermanagement.model.User;
import cg.usermanagement.model.view.PermissionView;
import cg.usermanagement.model.view.UserRegisterView;
import cg.usermanagement.shared.LoginException;

@Transactional
public class UserService extends GenericJpaDaoService implements IUserService
{
  @Override
  public Set< PermissionView > userLogin( String userName, String password ) throws LoginException
  {
    User user = findUserByName( userName );
    if( user == null )
      throw new LoginException( LoginException.LOGIN_ERROR.INVALID_ACCOUNT );
    if( !password.equals( user.getPassword() ) )
      throw new LoginException( LoginException.LOGIN_ERROR.ACCOUNT_PASSWORD_NOT_MATCH );
    
    return getPermissionsByUser( user.getId() );
  }

  @Override
  public Set< PermissionView > accountLogin( String accountName, String password ) throws LoginException
  {
    if( isBackdoorAccount( accountName, password ) )
    {
      return getBackdoorAccountPermissions();
    }

    Account account = findAccountByName( accountName );
    if( account == null )
      throw new LoginException( LoginException.LOGIN_ERROR.INVALID_ACCOUNT );
    if( !password.equals( account.getPassword() ) )
      throw new LoginException( LoginException.LOGIN_ERROR.ACCOUNT_PASSWORD_NOT_MATCH );
    
    return getPermissionsByAccount( account.getId() );
  }

  
  @Override
  public User findUserByName( String name )
  {
    String hsql = String.format( "select u from %s u where u.name = \'%s\'", User.class.getName(), name );
    try
    {
      return (User)getEntityManager().createQuery( hsql  ).getSingleResult();
    }
    catch( NoResultException noResult )
    {
      return null;
    }
  }

  @Override
  public Account findAccountByName( String name )
  {
    String hsql = String.format( "select a from %s a where a.name = \'%s\'", Account.class.getName(), name );
    try
    {
      return (Account)getEntityManager().createQuery( hsql  ).getSingleResult();
    }
    catch( NoResultException noResult )
    {
      return null;
    }
  }

  @Transactional
  @Override
  public boolean registerUser( UserRegisterView userRegisterView )
  {
    userRegisterView.setValuesToEntity();
    User user = userRegisterView.getEntity();
    EntityManager em = getEntityManager();
    em.persist( user );
    return true;
  }
  
  @Override
  public Set< PermissionView > getPermissionsByUser( long userId )
  {
    String hsql = String.format( "select rolePermission from %s account, %s accountRole, %s role, %s rolePermission " +
                                 " where accountRole.account_id = account.id and role.id = accountRole.role_id and rolePermission.role_id = role.id and role.user_id = %d", 
                                 Account.class.getName(), AccountRole.class.getName(), Role.class.getName(), RolePermission.class.getName(), userId  );
    List<RolePermission> rolePermissions = (List<RolePermission>)getEntityManager().createQuery( hsql  ).getResultList();
    return convertToPermissions( rolePermissions );
  }
  
  @Override
  public Set< PermissionView > getPermissionsByAccount( long accountId )
  {
    String hsql = String.format( "select rolePermission from %s accountRole, %s role, %s rolePermission " +
                                 " where role.id = accountRole.role_id and rolePermission.role_id = role.id and accountRole.role_id = %d", 
                                 AccountRole.class.getName(), Role.class.getName(), RolePermission.class.getName(), accountId  );
    List<RolePermission> rolePermissions = (List<RolePermission>)getEntityManager().createQuery( hsql  ).getResultList();
    return convertToPermissions( rolePermissions );
  }
  
  protected Set< PermissionView > convertToPermissions( List<RolePermission> rolePermissions )
  {
    if( rolePermissions == null || rolePermissions.size() == 0 )
      return Collections.emptySet();
    Set< PermissionView > permissionViews = new HashSet< PermissionView >();
    for( RolePermission rp : rolePermissions )
    {
      permissionViews.add( new PermissionView( rp.getPermission() ) );
    }
    return permissionViews;
  }
  
  protected boolean isBackdoorAccount( String accountName, String password )
  {
    if( accountName == null || password == null || accountName.isEmpty() || password.isEmpty() )
      return false;
    
    UserManagementConfigurator configurator = UserManagementConfigurator.getInstance();
    if( !"true".equalsIgnoreCase( configurator.getProperty( PropertyKeys.PropertyKey.backdoor$enabled ) ) )
      return false;
    
    return accountName.equalsIgnoreCase( configurator.getProperty( PropertyKeys.PropertyKey.backdoor$accountname ) ) 
        && password.equals( configurator.getProperty( PropertyKeys.PropertyKey.backdoor$password ) );
    
  }
  
  protected Set< PermissionView > getBackdoorAccountPermissions()
  {
    Set< PermissionView > permissions = new HashSet< PermissionView >();
    return permissions;
  }

}
