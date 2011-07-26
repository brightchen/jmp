package cg.gwt.components.api;

public @interface IResourceKey
{
  public String moduleName() default "";
  
  public String className() default "";
  
  public String propertyName() default "";
  
}
