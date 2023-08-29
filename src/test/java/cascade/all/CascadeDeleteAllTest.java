package cascade.all;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.github.wnameless.spring.boot.up.SpringBootUp;
import cascade.all.repository.CarRepository;
import cascade.all.repository.EngineRepository;
import cascade.all.repository.GasTankRepository;
import cascade.all.repository.MotorRepository;
import cascade.all.repository.WheelRepository;

@DirtiesContext
@ActiveProfiles("all")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApp.class)
public class CascadeDeleteAllTest {

  @Test
  public void testCascadeDeleteAllWithBulkOperationNoEffect() {
    SpringBootUp.getBean(CarRepository.class).deleteAll();

    assertEquals(DbInit.POPULATION_SIZE * 0, SpringBootUp.getBean(CarRepository.class).count());
    assertEquals(DbInit.POPULATION_SIZE * 1, SpringBootUp.getBean(MotorRepository.class).count());
    assertEquals(DbInit.POPULATION_SIZE * 1, SpringBootUp.getBean(EngineRepository.class).count());
    assertEquals(DbInit.POPULATION_SIZE * 1, SpringBootUp.getBean(GasTankRepository.class).count());
    assertEquals(DbInit.POPULATION_SIZE * 4, SpringBootUp.getBean(WheelRepository.class).count());
  }

}
