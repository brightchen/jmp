package cg.resourcemanagement.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // this annotation accessible at runtime via reflection.
public @interface IResourceKey
{
  public String moduleName() default "";
  
  public String className() default "";
  
  public String propertyName() default "";
  
}
