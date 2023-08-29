package cascade.all.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cascade.all.model.Motor;

@Repository
public interface MotorRepository extends MongoRepository<Motor, String> {}
