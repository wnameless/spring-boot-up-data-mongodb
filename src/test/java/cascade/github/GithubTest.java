package cascade.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import cascade.github.model.Car;
import cascade.github.model.Engine;
import cascade.github.model.GasTank;
import cascade.github.model.Motor;
import cascade.github.model.Wheel;
import cascade.github.repository.CarRepository;
import cascade.github.repository.EngineRepository;
import cascade.github.repository.GasTankRepository;
import cascade.github.repository.MotorRepository;
import cascade.github.repository.WheelRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApp.class)
public class GithubTest {

  @Autowired
  CarRepository carRepository;
  @Autowired
  GasTankRepository gasTankRepository;
  @Autowired
  EngineRepository engineRepository;
  @Autowired
  MotorRepository motorRepository;
  @Autowired
  WheelRepository wheelRepository;

  Car car = new Car();
  GasTank gasTank = new GasTank();
  Engine engine = new Engine();
  Motor motor = new Motor();
  Wheel frontRightWheel = new Wheel();
  Wheel frontLeftWheel = new Wheel();
  Wheel rareRightWheel = new Wheel();
  Wheel rareLeftWheel = new Wheel();

  @BeforeEach
  public void setUp() {
    carRepository.deleteAll();
    gasTankRepository.deleteAll();
    engineRepository.deleteAll();
    motorRepository.deleteAll();
    wheelRepository.deleteAll();

    car.setGasTank(gasTank);
    car.setEngine(engine);
    engine.setMotor(motor);
    car.setWheels(Arrays.asList(frontRightWheel, frontLeftWheel, rareRightWheel, rareLeftWheel));

    car = carRepository.save(car);
  }

  @Test
  public void testCascadeCreate() {
    assertEquals(1, carRepository.count());
    assertEquals(1, gasTankRepository.count());
    assertEquals(1, engineRepository.count());
    assertEquals(1, motorRepository.count());
    assertEquals(4, wheelRepository.count());
  }

  @Test
  public void testCascadeUpdate() {
    car.getEngine().setHorsePower(600);
    car.getWheels().get(0).setTireBrand("Goodyear");
    carRepository.save(car);
    car = carRepository.findById(car.getId()).get();

    // assertEquals(500, car.getEngine().getHorsePower());
    assertEquals("Goodyear", car.getWheels().get(0).getTireBrand());
  }

  @Test
  public void testParentRef() {
    assertSame(car, gasTank.getCar());
    assertSame(car, engine.getCar());
    assertSame(car, motor.getCar());
    assertSame(engine, motor.getEngine());
    assertSame(car, frontRightWheel.getCar());
    assertSame(car, frontLeftWheel.getCar());
    assertSame(car, rareRightWheel.getCar());
    assertSame(car, rareLeftWheel.getCar());
  }

}
