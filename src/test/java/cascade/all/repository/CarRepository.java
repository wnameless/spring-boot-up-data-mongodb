package cascade.all.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cascade.all.model.Car;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {}
