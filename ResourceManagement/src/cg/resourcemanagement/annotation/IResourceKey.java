package cg.resourcemanagement.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
 * the IResourceKey annotation can annotate on UIContentData class, the ResourceData class, 
 * or the setter/getter method of super-content-data or getter/setter of resource data
 */
@Retention(RetentionPolicy.RUNTIME) // this annotation accessible at runtime via reflection.
public @interface IResourceKey
{
  public String moduleName() default "";
  
  public String className() default "";
  
  public String propertyName() default "";
  
  /*
   * there are generic ContentData/ResourceData, for example ButtonData
   * the module/class name should not determined by this class itself, but determined by the owner content data
   * set inheritModuleName/inheritClassName to true represents they are inherited
   */
  public boolean inheritModuleName() default false;
  public boolean inheritClassName() default false;
}
