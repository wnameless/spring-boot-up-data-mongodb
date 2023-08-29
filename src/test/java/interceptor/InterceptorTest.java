package interceptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.github.wnameless.spring.boot.up.data.mongodb.interceptor.SourceAndDocument;
import com.jparams.verifier.tostring.ToStringVerifier;
import interceptor.model.BeforeAfterDeleteModel;
import interceptor.model.BeforeAfterModel;
import interceptor.repository.BeforeAfterDeleteModelRepository;
import interceptor.repository.BeforeAfterModelRepository;
import nl.jqno.equalsverifier.EqualsVerifier;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApp.class)
public class InterceptorTest {

  public static final List<String> messages = new ArrayList<>();
  public static final List<SourceAndDocument> sads = new ArrayList<>();

  @Autowired
  BeforeAfterModelRepository beforeAfterModelRepository;
  @Autowired
  BeforeAfterDeleteModelRepository beforeAfterDeleteModelRepository;

  @BeforeEach
  public void cleanUp() {
    beforeAfterModelRepository.deleteAll();
    beforeAfterDeleteModelRepository.deleteAll();
    messages.clear();
    sads.clear();
  }

  @Test
  public void testBeforeAfterEvent() {
    var model = beforeAfterModelRepository.save(new BeforeAfterModel());
    beforeAfterModelRepository.findById(model.getId());

    assertEquals(4, messages.size());
    assertEquals("beforeConvertToMongo", messages.get(0));
    assertEquals("beforeSaveToMongo", messages.get(1));
    assertEquals("afterSaveToMongo", messages.get(2));
    assertEquals("afterConvertFromMongo", messages.get(3));
    assertEquals(4, sads.size());
    for (int i = 0; i < 4; i++) {
      assertEquals(model.getId(), sads.get(i).getSource(BeforeAfterModel.class).getId());
    }
  }

  @Test
  public void testBeforeAfterDeleteFromMongoEvent() {
    var model = beforeAfterDeleteModelRepository.save(new BeforeAfterDeleteModel());
    beforeAfterDeleteModelRepository.delete(model);

    assertEquals(2, messages.size());
    assertEquals("beforeDeleteFromMongo", messages.get(0));
    assertEquals("afterDeleteFromMongo", messages.get(1));
    assertEquals(2, sads.size());
    assertEquals(model.getId(), sads.get(0).getSource(BeforeAfterDeleteModel.class).getId());
    assertEquals(model.getId(), sads.get(1).getSource(BeforeAfterDeleteModel.class).getId());

    assertTrue(sads.get(0).getSource() instanceof BeforeAfterDeleteModel);
    assertTrue(sads.get(0).getDocument() instanceof Document);
    assertTrue(sads.get(1).hasSource(BeforeAfterDeleteModel.class));
    assertEquals(sads.get(0), sads.get(1));
  }

  @Test
  public void testBeforeAfterDeleteFromMongoEventWithBulkOperationNoEffect() {
    beforeAfterDeleteModelRepository.save(new BeforeAfterDeleteModel());
    beforeAfterDeleteModelRepository.deleteAll();

    assertEquals(0, messages.size());
    assertEquals(0, sads.size());
  }

  @Test
  public void testSourceAndDocument() {
    EqualsVerifier.forClass(SourceAndDocument.class).verify();
    ToStringVerifier.forClass(SourceAndDocument.class).verify();
  }

}
