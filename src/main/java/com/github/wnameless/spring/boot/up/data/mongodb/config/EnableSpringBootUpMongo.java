package com.github.wnameless.spring.boot.up.data.mongodb.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * 
 * {@link EnableSpringBootUpMongo} actives Mongo Cascade, Annotation Driven Mongo Event and Mongo
 * Projection features for Spring Data MongoDB.
 * 
 * <br>
 * Add this annotation to a {@code @Configuration} class to enable EnableSpringBootUpMongo.<br>
 * <br>
 * For example:
 *
 * <pre class="code">
 * 
 * &#064;EnableSpringBootUpMongo(allowAnnotationDrivenEvent = true)
 * &#064;EnableSpringBootUp
 * &#064;Configuration
 * public class MyWebConfiguration {}
 * </pre>
 * 
 * The allowAnnotationDrivenEvent attribute determines whether Annotation Driven Event feature is
 * enable or disable. Default value is false.
 * 
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({SprinBootUpMongoRegistrar.class})
public @interface EnableSpringBootUpMongo {

  boolean allowAnnotationDrivenEvent() default false;

}
