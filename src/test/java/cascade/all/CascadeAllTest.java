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
public class CascadeAllTest {

  @Test
  public void testCascadeCreate() {
    assertEquals(DbInit.POPULATION_SIZE, SpringBootUp.getBean(CarRepository.class).count());
    assertEquals(DbInit.POPULATION_SIZE * 1, SpringBootUp.getBean(MotorRepository.class).count());
    assertEquals(DbInit.POPULATION_SIZE * 1, SpringBootUp.getBean(EngineRepository.class).count());
    assertEquals(DbInit.POPULATION_SIZE * 1, SpringBootUp.getBean(GasTankRepository.class).count());
    assertEquals(DbInit.POPULATION_SIZE * 4, SpringBootUp.getBean(WheelRepository.class).count());
  }

  @Test
  public void testCascadeUpdate() {
    var cars = SpringBootUp.getBean(CarRepository.class).findAll();
    cars.forEach(car -> {
      car.getEngine().setHorsePower(600);
      car.getGasTank().setCapacity(200);
    });
    SpringBootUp.getBean(CarRepository.class).saveAll(cars);

    cars = SpringBootUp.getBean(CarRepository.class).findAll();
    cars.forEach(car -> {
      assertEquals(600, car.getEngine().getHorsePower());
      assertEquals(200, car.getGasTank().getCapacity());
    });
  }

  @Test
  public void testCascadeDelete() {
    var cars = SpringBootUp.getBean(CarRepository.class).findAll();
    SpringBootUp.getBean(CarRepository.class).deleteAll(cars);

    assertEquals(0, SpringBootUp.getBean(CarRepository.class).count());
    assertEquals(0, SpringBootUp.getBean(MotorRepository.class).count());
    assertEquals(0, SpringBootUp.getBean(EngineRepository.class).count());
    assertEquals(0, SpringBootUp.getBean(GasTankRepository.class).count());
    assertEquals(0, SpringBootUp.getBean(WheelRepository.class).count());
  }

}
