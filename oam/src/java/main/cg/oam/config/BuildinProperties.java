package cg.oam.config;

import java.util.Properties;

public class BuildinProperties
{
  private static Properties props;
  
  public static Properties getBuildinProperties()
  {
    if( props != null )
      return props;
    
    props = new Properties();
    
    //ftp
    props.setProperty( "ftpTask.shouldRun", "false" );
    props.setProperty( "ftp.delay.start.milliseconds", "10000000" ); 
    props.setProperty( "ftp.period.milliseconds", "10000000" );
    props.setProperty( "ftp.usersFilePath", "c:\\temp" );
    props.setProperty( "ftp.usersFilePath", "c:\\temp" );
    props.setProperty( "ftp.usersFilePathNew", "c:\\temp" );
    props.setProperty( "mail.smtp.host", "localhost" );
    props.setProperty( "mail.smtp.username", "smtpuser" );
    props.setProperty( "mail.smtp.password", "smtppass" );
    props.setProperty( "mail.from", "mailfrom" );
    props.setProperty( "mail.iseepublish_server", "" );
    props.setProperty( "mail.template.dir", "c:\\temp" );
    props.setProperty( "ftp.ftpConfig", "ftpconfig" );
    props.setProperty( "ftp.localDownloadDir", "c:\\temp" );
    
    //db
    //derby
    //"jdbc:derby://localhost:1527/database;create=true"
    props.setProperty( "db.jdbc.driver_class", "org.apache.derby.jdbc.EmbeddedDriver" );
    props.setProperty( "db.jdbc.url", "jdbc:derby:test;create=true" );
    props.setProperty( "db.username", "user1" );
    props.setProperty( "db.password", "user1" );
    props.setProperty( "db.validation.query", "" );
    props.setProperty( "ajax.default.layout.filePath", "c:\\temp" );

    return props;
  }
}
