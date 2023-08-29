package cascade.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import cascade.all.model.Car;
import cascade.all.model.Engine;
import cascade.all.model.GasTank;
import cascade.all.model.Motor;
import cascade.all.model.Wheel;
import cascade.all.repository.CarRepository;
import cascade.all.repository.EngineRepository;
import cascade.all.repository.GasTankRepository;
import cascade.all.repository.MotorRepository;
import cascade.all.repository.WheelRepository;

@Profile("all")
@Component
public class DbInit {

  public static final int POPULATION_SIZE = 100;

  @Autowired
  CarRepository carRepo;
  @Autowired
  EngineRepository engineRepo;
  @Autowired
  GasTankRepository gasTankRepo;
  @Autowired
  MotorRepository motorRepo;
  @Autowired
  WheelRepository wheelRepo;

  @EventListener(ApplicationReadyEvent.class)
  void insertDocuments() {
    carRepo.deleteAll();
    engineRepo.deleteAll();
    gasTankRepo.deleteAll();
    motorRepo.deleteAll();
    wheelRepo.deleteAll();

    for (int i = 0; i < POPULATION_SIZE; i++) {
      Car car = new Car();

      Engine engine = new Engine();
      engine.setHorsePower(500);

      Motor motor = new Motor();
      motor.setRpm(60000);
      engine.setMotor(motor);

      GasTank gasTank = new GasTank();
      gasTank.setCapacity(100);

      car.setEngine(engine);
      car.setGasTank(gasTank);

      car.getFrontWheels().add(new Wheel("Michelin"));
      car.getFrontWheels().add(new Wheel("Goodyear"));
      car.getRareWheels().put("rareLeft", new Wheel("Continental"));
      car.getRareWheels().put("rareRight", new Wheel("Bridgestone"));

      carRepo.save(car);
    }
  }

}
