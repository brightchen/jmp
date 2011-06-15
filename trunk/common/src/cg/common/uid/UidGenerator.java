package cg.common.uid;


public class UidGenerator
{
  private static final int DEF_MODULE_SECTION_CAPACITY = 10;
  private static final int DEF_SECTION_ENTRY_CAPACITY = 1000;
  
  //max sections in each module
  private int moduleSectionCapacity = DEF_MODULE_SECTION_CAPACITY;
  
  //the max entries in each section
  private int sectionEntryCapacity = DEF_SECTION_ENTRY_CAPACITY;
  
  private int moduleEntryCapacity = moduleSectionCapacity * sectionEntryCapacity;
  
  private static UidGenerator defaultInstance;
  public static UidGenerator getDefaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( UidGenerator.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new UidGenerator();
        }
      }
    }
    
    return defaultInstance;
  }
  
  public long getUid( IUidData uidData, int localIndex )
  {
    if( localIndex >= sectionEntryCapacity )
      throw new RuntimeException( String.format( "the local index( %d ) is large than or equal section max entries ( %d ) ", localIndex, sectionEntryCapacity ) );
    long uid = uidData.getModuleId();
    uid *= moduleEntryCapacity;
    uid += uidData.getSectionId() * DEF_SECTION_ENTRY_CAPACITY + localIndex;
    return uid;
  }


  public int getModuleSectionCapacity()
  {
    return moduleSectionCapacity;
  }


  public void setModuleSectionCapacity( int moduleSectionCapacity )
  {
    this.moduleSectionCapacity = moduleSectionCapacity;
    reCalculateModuleEntryCapacity();
  }


  public int getSectionEntryCapacity()
  {
    return sectionEntryCapacity;
  }


  public void setSectionEntryCapacity( int sectionEntryCapacity )
  {
    this.sectionEntryCapacity = sectionEntryCapacity;
    reCalculateModuleEntryCapacity();
  }
  
  public void reCalculateModuleEntryCapacity()
  {
    moduleEntryCapacity = moduleSectionCapacity * sectionEntryCapacity;
  }
  
}
