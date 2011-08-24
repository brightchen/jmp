package cg.contentdata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cg.contentdata.management.ISubContentDataLookupStrategy;

/*
 * this annotation annotated the UIContentData class
 * if a class doesn't annotated by this annotation, treat it as leaf
 */
@Retention(RetentionPolicy.RUNTIME) // this annotation accessible at runtime via reflection.
@Target( { ElementType.TYPE } )       // This annotation can only be applied to class.
public @interface IContentDataAttributes
{
  //does this content data include sub content data, if set to false, the resource injection will not search for sub-content data
  //this field used to replaced UICompositeContentData as this interface is not necessary for the client code
  public boolean isComposite() default false;    
  
  /*
   * specify how to find the sub-content-data
   */
  public Class< ? extends ISubContentDataLookupStrategy > subContentDataLookupStrategy();
}
