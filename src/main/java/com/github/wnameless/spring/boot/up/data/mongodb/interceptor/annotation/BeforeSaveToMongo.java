package com.github.wnameless.spring.boot.up.data.mongodb.interceptor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;

/**
 * 
 * This interceptor annotation allows Annotation Driven Event to be triggered by
 * spring-boot-up-mongodb library. All methods annotated by this annotaion inside a Spring Data
 * MongoDB entity class will be executed on corresponding {@link MongoMappingEvent}.<br>
 * <br>
 * {@link BeforeSaveToMongo} indicates {@link BeforeSaveEvent}.
 * 
 * @author Wei-Ming Wu
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BeforeSaveToMongo {}
