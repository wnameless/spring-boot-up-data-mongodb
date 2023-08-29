package querydsl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import querydsl.model.ComplexModel;
import querydsl.model.NestedModel;
import querydsl.model.ProjectModel;
import querydsl.repository.ComplexModelRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApp.class)
public class MongoProjectionRepositoryTest {

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
  public void testProjectionByClass() {
    var model = complexModelRepository.findAllProjectedBy(ProjectModel.class).get(0);
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());
  }

  @Test
  public void testProjectionByPredicateAndClass() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");
    Predicate p = entityPath.getString("str").eq("str");

    var model = complexModelRepository.findAllProjectedBy(p, ProjectModel.class).get(0);
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());

    model = complexModelRepository.findProjectedBy(p, ProjectModel.class).get();
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());

    p = entityPath.getString("str").eq("none");
    assertTrue(complexModelRepository.findAllProjectedBy(p, ProjectModel.class).isEmpty());
  }

  @Test
  public void testProjectionByDotPath() {
    var model = complexModelRepository.findAllProjectedBy("str").get(0);
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());

    model = complexModelRepository.findAllProjectedBy("nested.s").get(0);
    assertNull(model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested().getF());
    assertEquals((short) 9, model.getNested().getS());
  }

  @Test
  public void testProjectionByPredicateAndDotPath() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");
    Predicate p = entityPath.getString("str").eq("str");

    var model = complexModelRepository.findAllProjectedBy(p, "str").get(0);
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());

    model = complexModelRepository.findProjectedBy(p, "str").get();
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());

    p = entityPath.getString("str").eq("none");
    assertTrue(complexModelRepository.findAllProjectedBy(p, "str").isEmpty());
  }

  @Test
  public void testProjectionByPath() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");

    var model = complexModelRepository.findAllProjectedBy(entityPath.getString("str")).get(0);
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());

    model = complexModelRepository
        .findAllProjectedBy(entityPath.getSimple("nested", NestedModel.class)).get(0);
    assertNull(model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertEquals(7.8f, model.getNested().getF());
    assertEquals((short) 9, model.getNested().getS());
  }

  @Test
  public void testProjectionByPredicateAndPath() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");
    Predicate p = entityPath.getString("str").eq("str");

    var model = complexModelRepository.findAllProjectedBy(p, entityPath.getString("str")).get(0);
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());

    model = complexModelRepository.findProjectedBy(p, entityPath.getString("str")).get();
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());

    p = entityPath.getString("str").eq("none");
    assertTrue(complexModelRepository.findAllProjectedBy(p, entityPath.getString("str")).isEmpty());
  }

  @Test
  public void testProjectionBySortAndClass() {
    var model =
        complexModelRepository.findAllProjectedBy(Sort.by("str"), ProjectModel.class).get(0);
    assertEquals("STR", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());
  }

  @Test
  public void testProjectionByPredicateAndSortAndClass() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");
    Predicate p = entityPath.getString("str").startsWith("s");

    var model =
        complexModelRepository.findAllProjectedBy(p, Sort.by("str"), ProjectModel.class).get(0);
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());
  }

  @Test
  public void testProjectionBySortAndDotPath() {
    var model = complexModelRepository.findAllProjectedBy(Sort.by("str"), "str").get(0);
    assertEquals("STR", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());
  }

  @Test
  public void testProjectionByPredicateAndSortAndDotPath() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");
    Predicate p = entityPath.getString("str").startsWith("s");

    var model = complexModelRepository.findAllProjectedBy(p, Sort.by("str"), "str").get(0);
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());
  }

  @Test
  public void testProjectionBySortAndPath() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");

    var model = complexModelRepository
        .findAllProjectedBy(Sort.by("str"), entityPath.getString("str")).get(0);
    assertEquals("STR", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());
  }

  @Test
  public void testProjectionByPredicateAndSortAndPath() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");
    Predicate p = entityPath.getString("str").startsWith("s");

    var model = complexModelRepository
        .findAllProjectedBy(p, Sort.by("str"), entityPath.getString("str")).get(0);
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());
  }

  @Test
  public void testProjectionByPageableAndClass() {
    Pageable pageable = PageRequest.of(1, 1, Sort.by("str"));
    var page = complexModelRepository.findPagedProjectedBy(pageable, ProjectModel.class);
    var model = page.iterator().next();
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());
  }

  @Test
  public void testProjectionByPredicateAndPageableAndClass() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");
    Predicate p = entityPath.getString("str").startsWith("s");

    Pageable pageable = PageRequest.of(0, 1, Sort.by("str"));
    var page = complexModelRepository.findPagedProjectedBy(p, pageable, ProjectModel.class);
    var model = page.iterator().next();
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());
  }

  @Test
  public void testProjectionByPageableAndDotPath() {
    Pageable pageable = PageRequest.of(1, 1, Sort.by("str"));
    var page = complexModelRepository.findPagedProjectedBy(pageable, "str");
    var model = page.iterator().next();
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());
  }

  @Test
  public void testProjectionByPredicateAndPageableAndDotPath() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");
    Predicate p = entityPath.getString("str").startsWith("s");

    Pageable pageable = PageRequest.of(0, 1, Sort.by("str"));
    var page = complexModelRepository.findPagedProjectedBy(p, pageable, "str");
    var model = page.iterator().next();
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());
  }

  @Test
  public void testProjectionByPageableAndPath() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");

    Pageable pageable = PageRequest.of(0, 1, Sort.by("str"));
    var page = complexModelRepository.findPagedProjectedBy(pageable, entityPath.getString("str"));
    var model = page.iterator().next();
    assertEquals("STR", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());
  }

  @Test
  public void testProjectionByPredicateAndPageableAndPath() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");
    Predicate p = entityPath.getString("str").startsWith("s");

    Pageable pageable = PageRequest.of(0, 1, Sort.by("str"));
    var page =
        complexModelRepository.findPagedProjectedBy(p, pageable, entityPath.getString("str"));
    var model = page.iterator().next();
    assertEquals("str", model.getStr());
    assertNull(model.getI());
    assertNull(model.getD());
    assertNull(model.getB());
    assertNull(model.getNested());
  }

}


