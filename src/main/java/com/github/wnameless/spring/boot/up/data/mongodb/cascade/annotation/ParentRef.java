package com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * If a field in Spring Data MongoDB document is annotated with {@link ParentRef}, the annotated
 * field value will be set by the Object which triggers the
 * {@link com.github.wnameless.spring.boot.up.data.mongodb.cascade.CascadeType CascadeType} ALL,
 * CREATE or UPDATE upon this field's object.
 * 
 * @author Wei-Ming Wu
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ParentRef {

  /**
   * The value, which is set into the annotated field, can be altered to any field of the Object
   * which triggers the Cascade by providing the coorosponding field name of the trigger Object
   * here.
   */
  String value() default "";

}
