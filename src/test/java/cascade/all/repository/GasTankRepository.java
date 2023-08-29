package cascade.all.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cascade.all.model.GasTank;

@Repository
public interface GasTankRepository extends MongoRepository<GasTank, String> {}
