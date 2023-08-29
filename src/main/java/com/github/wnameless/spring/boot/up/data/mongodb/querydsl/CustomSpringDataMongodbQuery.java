package com.github.wnameless.spring.boot.up.data.mongodb.querydsl;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.support.SpringDataMongodbQuery;
import com.querydsl.core.types.Predicate;

public class CustomSpringDataMongodbQuery<T> extends SpringDataMongodbQuery<T> {

  public CustomSpringDataMongodbQuery(MongoOperations operations, Class<? extends T> type) {
    super(operations, type);
  }

  @Override
  public Document createQuery(Predicate predicate) {
    return super.createQuery(predicate);
  }

}
