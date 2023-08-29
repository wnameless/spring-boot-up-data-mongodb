package cascade.github.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cascade.github.model.Motor;

@Repository
public interface MotorRepository extends MongoRepository<Motor, String> {}
