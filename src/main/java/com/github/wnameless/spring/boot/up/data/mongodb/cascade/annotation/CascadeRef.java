package com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.CascadeType;

/**
 * 
 * {@link CascadeRef} can only work with {@link org.springframework.data.mongodb.core.mapping.DBRef
 * DBRef}. It only deals with 4 {@link CascadeType} values: ALL, CREATE, UPDATE, DELETE.
 * 
 * @author Wei-Ming Wu
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CascadeRef {

  CascadeType[] value() default {CascadeType.ALL};

}
