package cg.usermanagement.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

import cg.common.util.StringUtil;
import cg.model.util.ViewUtil;
import cg.query.IQueryCriteria;
import cg.query.QueryCriteriaBuilder;
import cg.query.SmartQueryBuilder;
import cg.usermanagement.api.IUserService;
import cg.usermanagement.api.UserSearchCriteria;
import cg.usermanagement.config.PropertyKeys;
import cg.usermanagement.config.UserManagementConfigurator;
import cg.usermanagement.model.Account;
import cg.usermanagement.model.AccountRole;
import cg.usermanagement.model.Role;
import cg.usermanagement.model.RolePermission;
import cg.usermanagement.model.User;
import cg.usermanagement.model.view.PermissionView;
import cg.usermanagement.model.view.RoleView;
import cg.usermanagement.model.view.UserRegisterView;
import cg.usermanagement.model.view.UserSearchView;
import cg.usermanagement.permission.UserManagementPermission;
import cg.usermanagement.shared.LoginException;
import cg.usermanagement.shared.RoleException;

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
    return findEntityByName( User.class, name, false );
  }
  
  @Override
  public List< UserSearchView > findUsers( UserSearchCriteria criteria )
  {
    IQueryCriteria queryCriteria = QueryCriteriaBuilder.defaultInstance().buildEqualsCriteria( User.class, criteria );
    String query = SmartQueryBuilder.defaultInstance().buildSearchHsql( User.class, queryCriteria );
    @SuppressWarnings( "unchecked" )
    List<User> users = (List<User>)getEntityManager().createQuery( query  ).getResultList();
    return ViewUtil.entitiesToReadableViews( users, UserSearchView.class );
  }

  @Override
  public Account findAccountByName( String name )
  {
    return findEntityByName( Account.class, name, false );
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
  @SuppressWarnings( "unchecked" )
  public Set< PermissionView > getPermissionsByUser( long userId )
  {
    String hsql = String.format( "select rolePermission from %s account, %s accountRole, %s role, %s rolePermission " +
                                 " where accountRole.account = account.id and role.id = accountRole.role and rolePermission.role = role.id and account.user = %d", 
                                 Account.class.getName(), AccountRole.class.getName(), Role.class.getName(), RolePermission.class.getName(), userId  );
    List<RolePermission> rolePermissions = (List<RolePermission>)getEntityManager().createQuery( hsql  ).getResultList();
    return convertToPermissions( rolePermissions );
  }
  
  @SuppressWarnings( "unchecked" )
  @Override
  public Set< PermissionView > getPermissionsByAccount( long accountId )
  {
    String hsql = String.format( "select rolePermission from %s accountRole, %s role, %s rolePermission " +
                                 " where role.id = accountRole.role and rolePermission.role = role.id and accountRole.role = %d", 
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
    permissions.add( new PermissionView( UserManagementPermission.USER_READ.toPermission() ) );
    permissions.add( new PermissionView( UserManagementPermission.USER_CREATE.toPermission() ) );
    return permissions;
  }

  @Override
  @Transactional
  public RoleView addRole( String roleName ) throws RoleException
  {
    if( StringUtil.isNullOrEmpty( roleName ) )
    {
      throw new RoleException( RoleException.ROLE_ERROR.ROLE_NAME_IS_EMPTY );
    }
    if( findRoleByName( roleName ) != null )
    {
      throw new RoleException( RoleException.ROLE_ERROR.ROLE_WITH_SAME_NAME_EXISTS );
    }

    Role role = new Role();
    role.setName( roleName );
    role = saveEntityInternal( role );
    return new RoleView( role );
  }

  @Override
  public Role findRoleByName( String roleName )
  {
    return findEntityByName( Role.class, roleName, false );
  }
}
