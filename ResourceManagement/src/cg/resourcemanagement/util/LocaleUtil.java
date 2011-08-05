package cg.resourcemanagement.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import cg.common.util.StringUtil;

public class LocaleUtil
{
  // the top locale is the locale without specifying language/country/variant
  public static final Locale TOP_LOCALE = new Locale( "" );

  private static final String SEPERATOR = "_";
  
  /*
   * the localeName must have format language_country_variant
   */
  public static Locale getLocale( String localeName )
  {
    if( localeName == null || localeName.isEmpty() )
      return TOP_LOCALE;
    if( localeName.startsWith( SEPERATOR ) )
      return getLocale( localeName.substring( SEPERATOR.length() ) );
    String[] localeInfos = localeName.split( SEPERATOR );
    switch( localeInfos.length )
    {
      case 1:
        return new Locale( localeInfos[0] );
      case 2:
        return new Locale( localeInfos[0], localeInfos[1] );
      case 3:
        return new Locale( localeInfos[0], localeInfos[1], localeInfos[2] );
    }
    throw new IllegalArgumentException( "method: getLocale(); localeName: " + localeName );
  }
  
  /*
   * this method is opposite to the getLocale, it returns language_country_variant
   */
  public static String getLocaleName( Locale locale )
  {
    if( locale == null )
      locale = TOP_LOCALE;
    return String.format( "%s" + SEPERATOR + "%s" + SEPERATOR + "%s" , StringUtil.nullToEmpty( locale.getLanguage() ), 
                          StringUtil.nullToEmpty( locale.getCountry() ), StringUtil.nullToEmpty( locale.getVariant() ) );
  }
  
  public static Set< Locale > getLocales( Set< String > localeNames )
  {
    if( localeNames == null || localeNames.isEmpty() )
      return Collections.emptySet();
    
    Set< Locale > locales = new HashSet< Locale >();
    for( String localeName : localeNames )
    {
      locales.add( getLocale( localeName ) );
    }
    return locales;
  }
}
