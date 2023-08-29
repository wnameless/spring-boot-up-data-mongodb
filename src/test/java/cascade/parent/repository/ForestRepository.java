package cascade.parent.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cascade.parent.model.Forest;

@Repository
public interface ForestRepository extends MongoRepository<Forest, String> {}
