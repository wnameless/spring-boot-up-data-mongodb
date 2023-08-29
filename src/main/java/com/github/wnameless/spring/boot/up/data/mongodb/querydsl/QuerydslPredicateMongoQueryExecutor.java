package com.github.wnameless.spring.boot.up.data.mongodb.querydsl;

import java.util.List;
import java.util.function.Function;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import com.github.wnameless.spring.boot.up.SpringBootUp;
import com.querydsl.core.types.Predicate;

/**
 * 
 * {@link QuerydslPredicateMongoQueryExecutor} bridge all {@link MongoOperations} method arguments
 * which are between Spring Data MongoDB {@link Query} and QueryDSL {@link Predicate}.
 * 
 */
public interface QuerydslPredicateMongoQueryExecutor<E> {

  default MongoOperations getMongoOperations() {
    return SpringBootUp.getBean(MongoOperations.class);
  }

  default E findOne(Query query, Class<E> entityClass) {
    MongoOperations mongoOperations = getMongoOperations();
    return mongoOperations.findOne(query, entityClass);
  }

  default E findOne(Query query, Class<E> entityClass, Function<Query, Query> queryConfig) {
    MongoOperations mongoOperations = getMongoOperations();

    query = queryConfig.apply(query);

    return mongoOperations.findOne(query, entityClass);
  }

  default E findOne(Predicate predicate, Class<E> entityClass) {
    return findOne(predicate, entityClass, q -> q);
  }

  default E findOne(Predicate predicate, Class<E> entityClass, Function<Query, Query> queryConfig) {
    MongoOperations mongoOperations = getMongoOperations();

    Document document =
        new CustomSpringDataMongodbQuery<>(mongoOperations, entityClass).createQuery(predicate);
    Query query = Query.query(new DocumentCriteria(document));
    query = queryConfig.apply(query);

    return mongoOperations.findOne(query, entityClass);
  }

  default List<E> findAll(Query query, Class<E> entityClass) {
    MongoOperations mongoOperations = getMongoOperations();
    return mongoOperations.find(query, entityClass);
  }

  default List<E> findAll(Query query, Class<E> entityClass, Function<Query, Query> queryConfig) {
    MongoOperations mongoOperations = getMongoOperations();

    query = queryConfig.apply(query);

    return mongoOperations.find(query, entityClass);
  }

  default List<E> findAll(Predicate predicate, Class<E> entityClass) {
    return findAll(predicate, entityClass, q -> q);
  }

  default List<E> findAll(Predicate predicate, Class<E> entityClass,
      Function<Query, Query> queryConfig) {
    MongoOperations mongoOperations = getMongoOperations();

    Document document =
        new CustomSpringDataMongodbQuery<>(mongoOperations, entityClass).createQuery(predicate);
    Query query = Query.query(new DocumentCriteria(document));
    query = queryConfig.apply(query);

    return mongoOperations.find(query, entityClass);
  }

  default long countAll(Query query, Class<E> entityClass) {
    MongoOperations mongoOperations = getMongoOperations();
    return mongoOperations.count(query, entityClass);
  }

  default long countAll(Query query, Class<E> entityClass, Function<Query, Query> queryConfig) {
    MongoOperations mongoOperations = getMongoOperations();

    query = queryConfig.apply(query);

    return mongoOperations.count(query, entityClass);
  }

  default long countAll(Predicate predicate, Class<E> entityClass) {
    return countAll(predicate, entityClass, q -> q);
  }

  default long countAll(Predicate predicate, Class<E> entityClass,
      Function<Query, Query> queryConfig) {
    MongoOperations mongoOperations = getMongoOperations();

    Document document =
        new CustomSpringDataMongodbQuery<>(mongoOperations, entityClass).createQuery(predicate);
    Query query = Query.query(new DocumentCriteria(document));
    query = queryConfig.apply(query);

    return mongoOperations.count(query, entityClass);
  }

}
