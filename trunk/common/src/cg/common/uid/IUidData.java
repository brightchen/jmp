package cg.common.uid;

/*
 * the data required to generate the uid
 * this interface is used by feature/permission to generate the unique and reproduceable uid 
 */
public interface IUidData
{
  // a system can have multiple modules
  public int getModuleId(); 
  // one module can have multiple Entries
  public int getSectionId();
}
