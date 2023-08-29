package com.github.wnameless.spring.boot.up.data.mongodb.querydsl;

import org.bson.Document;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;

/**
 * 
 * {@link DocumentCriteria} is designed to convert a MongoDB query {@link Document} to a
 * {@link CriteriaDefinition}, then {@link CustomSpringDataMongodbQuery} can create a QueryDSL
 * {@link com.querydsl.core.types.Predicate Predicate} from it.
 * 
 */
public class DocumentCriteria implements CriteriaDefinition {

  private final Document criteriaObject;

  public DocumentCriteria(Document criteriaObject) {
    this.criteriaObject = criteriaObject;
  }

  @Override
  public Document getCriteriaObject() {
    return criteriaObject;
  }

  @Override
  public String getKey() {
    return null;
  }

}
