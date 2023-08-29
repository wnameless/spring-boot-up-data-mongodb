package querydsl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.github.wnameless.spring.boot.up.data.mongodb.querydsl.MongoQuerydslUtils;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathBuilder;
import querydsl.model.ComplexModel;

public class MongoQuerydslUtilsCompleteTest {

  @Test
  public void testFindDotPathsByClassException() {
    assertThrows(RuntimeException.class, () -> {
      MongoQuerydslUtils.findDotPaths(List.class);
    });
  }

  @Test
  public void testFindDotPathsByPathWithEmptyResult() {
    PathBuilder<ComplexModel> entityPath = new PathBuilder<>(ComplexModel.class, "entity");

    assertEquals(0, MongoQuerydslUtils.findDotPaths(convertPathArgs(entityPath.getRoot())).length);
  }

  private Path<?>[] convertPathArgs(Path<?>... paths) {
    return paths;
  }

}
