package cascade.github.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cascade.github.model.Wheel;

@Repository
public interface WheelRepository extends MongoRepository<Wheel, String> {}
