package cascade.all.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cascade.all.model.Engine;

@Repository
public interface EngineRepository extends MongoRepository<Engine, String> {}
