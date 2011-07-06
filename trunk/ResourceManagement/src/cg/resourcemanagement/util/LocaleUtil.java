package cg.resourcemanagement.util;

import java.util.Locale;

public class LocaleUtil
{
  // the top locale is the locale without specifying language/country/variant
  public static Locale TOP_LOCALE = new Locale( "" );
  private static final String SEPERATOR = "_";
  
  public static Locale getLocale( String localeName )
  {
    if( localeName == null || localeName.isEmpty() )
      return TOP_LOCALE;
    if( localeName.startsWith( SEPERATOR ) )
      return getLocale( localeName.substring( SEPERATOR.length() ) );
    String[] localeInfos = localeName.split( SEPERATOR );
    if( localeInfos.length >=2 )
      return new Locale( localeInfos[0], localeInfos[1] );
    return new Locale( localeName );
  }
  
  /*
   * this method is opposite to the getLocale, it returns language_country_variant
   */
  public static String getLocaleName( Locale locale )
  {
    String localeName = locale.getLanguage();
    
    {
      String country = locale.getCountry();
      if( country != null && !country.isEmpty() )
        localeName += "_" + country;
    }
    
    {
      String variant = locale.getVariant();
      if( variant != null && !variant.isEmpty() )
        localeName += "_" + variant;
    }
    return localeName;
  }
}
