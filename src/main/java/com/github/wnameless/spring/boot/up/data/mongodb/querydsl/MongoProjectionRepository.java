package com.github.wnameless.spring.boot.up.data.mongodb.querydsl;

import static com.github.wnameless.spring.boot.up.data.mongodb.querydsl.MongoQuerydslUtils.findDotPaths;
import java.util.List;
import java.util.Optional;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;

/**
 * 
 * {@link MongoProjectionRepository} provides the missing database projection feature for Spring
 * Data MongoDB {@link MongoRepository}.
 * 
 * @author Wei-Ming Wu
 * 
 */
public interface MongoProjectionRepository<E> extends QuerydslPredicateMongoQueryExecutor<E> {

  @SuppressWarnings("unchecked")
  default Class<E> getDocumentType() {
    ResolvableType t = ResolvableType.forClass(getClass()).as(MongoProjectionRepository.class);
    return (Class<E>) t.getGeneric(0).resolve();
  }

  default Optional<E> findProjectedBy(Predicate predicate, Path<?>... paths) {
    return findProjectedBy(predicate, findDotPaths(paths));
  }

  default Optional<E> findProjectedBy(Predicate predicate, Class<?> projection) {
    return findProjectedBy(predicate, findDotPaths(projection));
  }

  default Optional<E> findProjectedBy(Predicate predicate, String... dotPaths) {
    E target = findOne(predicate, getDocumentType(), q -> {
      q.fields().include(dotPaths);
      return q;
    });

    return Optional.ofNullable(target);
  }

  default List<E> findAllProjectedBy(Path<?>... paths) {
    return findAllProjectedBy(findDotPaths(paths));
  }

  default List<E> findAllProjectedBy(Class<?> projection) {
    return findAllProjectedBy(findDotPaths(projection));
  }

  default List<E> findAllProjectedBy(String... dotPaths) {
    return findAll(new Query(), getDocumentType(), q -> {
      q.fields().include(dotPaths);
      return q;
    });
  }

  default List<E> findAllProjectedBy(Predicate predicate, Path<?>... paths) {
    return findAllProjectedBy(predicate, findDotPaths(paths));
  }

  default List<E> findAllProjectedBy(Predicate predicate, Class<?> projection) {
    return findAllProjectedBy(predicate, findDotPaths(projection));
  }

  default List<E> findAllProjectedBy(Predicate predicate, String... dotPaths) {
    return findAll(predicate, getDocumentType(), q -> {
      q.fields().include(dotPaths);
      return q;
    });
  }

  default List<E> findAllProjectedBy(Sort sort, Path<?>... paths) {
    return findAllProjectedBy(sort, findDotPaths(paths));
  }

  default List<E> findAllProjectedBy(Sort sort, Class<?> projection) {
    return findAllProjectedBy(sort, findDotPaths(projection));
  }

  default List<E> findAllProjectedBy(Sort sort, String... dotPaths) {
    Query query = new Query();
    query.with(sort);

    return findAll(query, getDocumentType(), q -> {
      q.fields().include(dotPaths);
      return q;
    });
  }

  default List<E> findAllProjectedBy(Predicate predicate, Sort sort, Path<?>... paths) {
    return findAllProjectedBy(predicate, sort, findDotPaths(paths));
  }

  default List<E> findAllProjectedBy(Predicate predicate, Sort sort, Class<?> projection) {
    return findAllProjectedBy(predicate, sort, findDotPaths(projection));
  }

  default List<E> findAllProjectedBy(Predicate predicate, Sort sort, String... dotPaths) {
    return findAll(predicate, getDocumentType(), q -> {
      q.fields().include(dotPaths);
      q.with(sort);
      return q;
    });
  }

  default Page<E> findPagedProjectedBy(Pageable pageable, Path<?>... paths) {
    return findPagedProjectedBy(pageable, findDotPaths(paths));
  }

  default Page<E> findPagedProjectedBy(Pageable pageable, Class<?> projection) {
    return findPagedProjectedBy(pageable, findDotPaths(projection));
  }

  default Page<E> findPagedProjectedBy(Pageable pageable, String... dotPaths) {
    Query query = new Query();
    query.with(pageable);

    List<E> targets = findAll(query, getDocumentType(), q -> {
      q.fields().include(dotPaths);
      return q;
    });
    long count = countAll(query, getDocumentType());

    return new PageImpl<>(targets, pageable, count);
  }

  default Page<E> findPagedProjectedBy(Predicate predicate, Pageable pageable, Path<?>... paths) {
    return findPagedProjectedBy(predicate, pageable, findDotPaths(paths));
  }

  default Page<E> findPagedProjectedBy(Predicate predicate, Pageable pageable,
      Class<?> projection) {
    return findPagedProjectedBy(predicate, pageable, findDotPaths(projection));
  }

  default Page<E> findPagedProjectedBy(Predicate predicate, Pageable pageable, String... dotPaths) {
    List<E> targets = findAll(predicate, getDocumentType(), q -> {
      q.fields().include(dotPaths);
      q.with(pageable);
      return q;
    });
    long count = countAll(predicate, getDocumentType(), q -> q.with(pageable));

    return new PageImpl<>(targets, pageable, count);
  }

}
