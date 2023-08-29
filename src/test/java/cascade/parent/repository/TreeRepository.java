package cascade.parent.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cascade.parent.model.Tree;

@Repository
public interface TreeRepository extends MongoRepository<Tree, String> {}
