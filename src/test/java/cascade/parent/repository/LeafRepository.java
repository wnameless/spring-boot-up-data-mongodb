package cascade.parent.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cascade.parent.model.Leaf;

@Repository
public interface LeafRepository extends MongoRepository<Leaf, String> {}
