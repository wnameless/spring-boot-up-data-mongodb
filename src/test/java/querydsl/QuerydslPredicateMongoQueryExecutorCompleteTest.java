package querydsl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.github.wnameless.spring.boot.up.data.mongodb.querydsl.CustomSpringDataMongodbQuery;
import com.github.wnameless.spring.boot.up.data.mongodb.querydsl.DocumentCriteria;
import com.github.wnameless.spring.boot.up.data.mongodb.querydsl.QuerydslPredicateMongoQueryExecutor;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import querydsl.model.ComplexModel;
import querydsl.model.NestedModel;
import querydsl.repository.ComplexModelRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApp.class)
public class QuerydslPredicateMongoQueryExecutorCompleteTest {

  @Autowired
  MongoOperations mongoOperations;

  @Autowired
  QuerydslPredicateMongoQueryExecutor<ComplexModel> querydslPredicateMongoQueryExecutor;
  @Autowired
  ComplexModelRepository complexModelRepository;

  @BeforeEach
  public void cleanUp() {
    complexModelRepository.deleteAll();

    var model = new ComplexModel();
    model.setStr("str");
    model.setI(123);
    model.setD(45.6);
    model.setB(true);
    var nested = new NestedModel();
    nested.setF(7.8f);
    nested.setS((short) 9);
    model.setNested(nested);
    complexModelRepository.save(model);

    model = new ComplexModel();
    model.setStr("STR");
    model.setI(123);
    model.setD(45.6);
    model.setB(true);
    nested = new NestedModel();
    nested.setF(7.8f);
    nested.setS((short) 9);
    model.setNested(nested);
    complexModelRepository.save(model);
  }

  @Test
  public void testgetMongoOperations() {
    assertSame(mongoOperations, querydslPredicateMongoQueryExecutor.getMongoOperations());
  }

  @Test
  public void testFindOne() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");
    Predicate p = entityPath.getString("str").startsWith("s");
    assertEquals(complexModelRepository.findByStrStartsWith("s"),
        querydslPredicateMongoQueryExecutor.findOne(p, ComplexModel.class));

    Document document =
        new CustomSpringDataMongodbQuery<>(mongoOperations, ComplexModel.class).createQuery(p);
    Query query = Query.query(new DocumentCriteria(document));
    assertEquals(complexModelRepository.findByStrStartsWith("s"),
        querydslPredicateMongoQueryExecutor.findOne(query, ComplexModel.class));


    p = entityPath.getString("str").isNotNull();
    document =
        new CustomSpringDataMongodbQuery<>(mongoOperations, ComplexModel.class).createQuery(p);
    query = Query.query(new DocumentCriteria(document));
    assertEquals(complexModelRepository.findByStrStartsWith("S"),
        querydslPredicateMongoQueryExecutor.findOne(query, ComplexModel.class,
            q -> q.with(Sort.by("str"))));
  }

  @Test
  public void testFindAll() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");
    Predicate p = entityPath.getString("str").startsWith("s");

    Document document =
        new CustomSpringDataMongodbQuery<>(mongoOperations, ComplexModel.class).createQuery(p);
    Query query = Query.query(new DocumentCriteria(document));

    var entities = querydslPredicateMongoQueryExecutor.findAll(query, ComplexModel.class);
    assertEquals(1, entities.size());
    assertEquals(complexModelRepository.findByStrStartsWith("s"), entities.get(0));

    entities = querydslPredicateMongoQueryExecutor.findAll(p, ComplexModel.class);
    assertEquals(1, entities.size());
    assertEquals(complexModelRepository.findByStrStartsWith("s"), entities.get(0));
  }

  @Test
  public void testCountAll() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");
    Predicate p = entityPath.getString("str").startsWith("s");

    assertEquals(1, querydslPredicateMongoQueryExecutor.countAll(p, ComplexModel.class));

    p = entityPath.getString("str").isNotNull();
    Document document =
        new CustomSpringDataMongodbQuery<>(mongoOperations, ComplexModel.class).createQuery(p);
    Query query = Query.query(new DocumentCriteria(document));
    var count = querydslPredicateMongoQueryExecutor.countAll(query, ComplexModel.class,
        q -> q.with(Sort.by("str")));
    assertEquals(2, count);
  }

}
