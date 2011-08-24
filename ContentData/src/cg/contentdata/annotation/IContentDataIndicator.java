package cg.contentdata.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
 * this annotation annotated the setter/getter content-data method to represent the parameter/return-value is a content-data
 * the resource injection can use this indicator to find the sub-content-datas of a contend-data 
 */
@Retention(RetentionPolicy.RUNTIME) // this annotation accessible at runtime via reflection.
public @interface IContentDataIndicator
{
  /*
   * the resource-injection treat the annotated getter/setter is a sub-content-data is value is true
   * else, don't treat as sub-content-data
   */
  public boolean isContentData() default true;
}
