package com.github.wnameless.spring.boot.up.data.mongodb.cascade;

import com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation.CascadeRef;

/**
 * 
 * {@linl CascadeType} includes 4 major cascade strategies for {@link CascadeRef} annotated fields.
 * 
 * @author Wei-Ming Wu
 * 
 */
public enum CascadeType {

  /**
   * Combines CREATE, UPDATE and DELETE
   */
  ALL,
  /**
   * Cascades target field while the {@link CascadeRef} annotated document is creating
   */
  CREATE,
  /**
   * Cascades target field while the {@link CascadeRef} annotated document is updating
   */
  UPDATE,
  /**
   * Cascades target field while the {@link CascadeRef} annotated document is deleting
   */
  DELETE;

}
