package cg.usermanagement.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyPlaceholderConfigurator extends PropertyPlaceholderConfigurer
{
  @Override
  protected String resolveSystemProperty( String key )
  {
    return UserManagementConfigurator.getInstance().getProperty( key );
  }

}
