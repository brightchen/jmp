package cg.usermanagement.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyPlaceholderConfigurator extends PropertyPlaceholderConfigurer
{
  @Override
  protected String resolveSystemProperty( String key )
  {
    String value = UserManagementConfigurator.getInstance().getProperty( key );
    return value;
  }

}
