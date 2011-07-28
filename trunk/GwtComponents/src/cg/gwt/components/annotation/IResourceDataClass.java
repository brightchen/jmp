package cg.gwt.components.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cg.gwt.components.shared.data.ResourceData;

@Retention(RetentionPolicy.RUNTIME) // this annotation accessible at runtime via reflection.
@Target( { ElementType.TYPE } )       // This annotation can only be applied to class.
public @interface IResourceDataClass
{
  public Class<? extends ResourceData > resourceDataClass();
}
