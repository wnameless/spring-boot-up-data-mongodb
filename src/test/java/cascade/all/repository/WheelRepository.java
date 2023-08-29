package cascade.all.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cascade.all.model.Wheel;

@Repository
public interface WheelRepository extends MongoRepository<Wheel, String> {}
